package com.takhir.animetitlesjava.requests;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.takhir.animetitlesjava.AppExecutors;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;
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
    private final MutableLiveData<List<KitsuAnime>> mAnimeList;
    private RetrieveAnimeListRunnable mRetrieveAnimeListRunnable;

    private AnimeApiClient() {
        mAnimeList = new MutableLiveData<>();
    }

    public static AnimeApiClient getInstance() {
        if (instance == null) {
            instance = new AnimeApiClient();
        }
        return instance;
    }

    public LiveData<List<KitsuAnime>> getAnimeList() {
        return mAnimeList;
    }

    public void searchAnimeListApi(String text, int pageLimit, int pageOffset) {
        if (mRetrieveAnimeListRunnable != null) {
            mRetrieveAnimeListRunnable = null;
        }
        mRetrieveAnimeListRunnable = new RetrieveAnimeListRunnable(text, pageLimit, pageOffset);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveAnimeListRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveAnimeListRunnable implements Runnable {
        private final String text;
        private final int pageLimit;
        private final int pageOffset;
        private boolean cancelRequest;

        public RetrieveAnimeListRunnable(String text, int pageLimit, int pageOffset) {
            this.text = text;
            this.pageLimit = pageLimit;
            this.pageOffset = pageOffset;
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
                    List<KitsuAnime> list = new ArrayList<>((response.body()).getAnime());
                    if (pageOffset == 1) {
                        mAnimeList.postValue(list);
                    } else {
                        List<KitsuAnime> currentList = mAnimeList.getValue();
                        mAnimeList.postValue(currentList);
                    }
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

    public void cancelRequest() {
        if (mRetrieveAnimeListRunnable != null) {
            mRetrieveAnimeListRunnable.cancelRequest();
        }
    }
}
