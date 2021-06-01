package com.takhir.animetitlesjava.requests;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.takhir.animetitlesjava.AppExecutors;
import com.takhir.animetitlesjava.models.kitsu.Anime;
import com.takhir.animetitlesjava.models.kitsu.Genres;
import com.takhir.animetitlesjava.requests.responses.AnimeGenresResponse;
import com.takhir.animetitlesjava.requests.responses.AnimeSearchResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.takhir.animetitlesjava.util.Constants.NETWORK_TIMEOUT;

public class AnimeApiClient {
    private static final String TAG = "AnimeApiClient";

    private static AnimeApiClient instance;
    private final MutableLiveData<List<Anime>> mAnimeList;
    private RetrieveAnimeListRunnable mRetrieveAnimeListRunnable;
    private final MutableLiveData<List<Genres>> mAnimeGenres;
    private RetrieveAnimeRunnable mRetrieveAnimeGenresRunnable;
    private final MutableLiveData<Boolean> mGenresRequestTimeout;

    private AnimeApiClient() {
        mAnimeList = new MutableLiveData<>();
        mAnimeGenres = new MutableLiveData<>();
        mGenresRequestTimeout = new MutableLiveData<>();
    }

    public static AnimeApiClient getInstance() {
        if (instance == null) {
            instance = new AnimeApiClient();
        }
        return instance;
    }

    public LiveData<List<Anime>> getAnimeList() {
        return mAnimeList;
    }

    public LiveData<List<Genres>> getAnimeGenres() {
        return mAnimeGenres;
    }

    public LiveData<Boolean> isGenresRequestTimedOut() {
        return mGenresRequestTimeout;
    }

    public void searchAnimeListApi(String text, int pageLimit, int pageOffset, boolean isNewSearch) {
        if (mRetrieveAnimeListRunnable != null) {
            mRetrieveAnimeListRunnable = null;
        }
        mRetrieveAnimeListRunnable = new RetrieveAnimeListRunnable(text, pageLimit, pageOffset, isNewSearch);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveAnimeListRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public void searchAnimeGenresById(int id) {
        if (mRetrieveAnimeGenresRunnable != null) {
            mRetrieveAnimeGenresRunnable = null;
        }
        mRetrieveAnimeGenresRunnable = new RetrieveAnimeRunnable(id);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveAnimeGenresRunnable);

        mGenresRequestTimeout.setValue(false);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
                mGenresRequestTimeout.postValue(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveAnimeListRunnable implements Runnable {
        private final String text;
        private final int pageLimit;
        private final int pageOffset;
        private final boolean isNewSearch;
        private boolean cancelRequest;

        public RetrieveAnimeListRunnable(String text, int pageLimit, int pageOffset, boolean isNewSearch) {
            this.text = text;
            this.pageLimit = pageLimit;
            this.pageOffset = pageOffset;
            this.isNewSearch = isNewSearch;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response<AnimeSearchResponse> response = getAnimeList(text, pageLimit, pageOffset).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<Anime> list = new ArrayList<>((response.body()).getAnime());
                    if (pageOffset == 1) {
                        mAnimeList.postValue(list);
                    } else {
                        List<Anime> currentList = mAnimeList.getValue();
                        assert currentList != null;
                        currentList.addAll(list);
                        mAnimeList.postValue(currentList);
                    }
                    if (isNewSearch) mAnimeList.postValue(null);
                } else {
                    assert response.errorBody() != null;
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: ERROR" + error);
                    mAnimeList.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mAnimeList.postValue(null);
            }
        }

        private Call<AnimeSearchResponse> getAnimeList(String text, int pageLimit, int pageOffset) {
            return ServiceGenerator.getAnimeAPI().searchAnime(text, pageLimit, pageOffset);
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request");
            cancelRequest = true;
        }
    }

    private class RetrieveAnimeRunnable implements Runnable {
        private final int id;
        private boolean cancelRequest;

        public RetrieveAnimeRunnable(int id) {
            this.id = id;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response<AnimeGenresResponse> response = getAnimeGenres(id).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<Genres> genres = new ArrayList<>((response.body()).getGenres());
                    mAnimeGenres.postValue(genres);
                } else {
                    assert response.errorBody() != null;
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: ERROR" + error);
                    mAnimeGenres.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mAnimeGenres.postValue(null);
            }
        }

        private Call<AnimeGenresResponse> getAnimeGenres(int id) {
            return ServiceGenerator.getAnimeAPI().getAnimeGenresById(id);
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling the search request");
            cancelRequest = true;
        }
    }

    public void cancelRequest() {
        if (mRetrieveAnimeListRunnable != null) {
            mRetrieveAnimeListRunnable.cancelRequest();
        }
    }
}
