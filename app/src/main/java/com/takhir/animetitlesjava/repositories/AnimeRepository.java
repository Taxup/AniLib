package com.takhir.animetitlesjava.repositories;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.takhir.animetitlesjava.models.kitsu.Anime;
import com.takhir.animetitlesjava.models.kitsu.Genres;
import com.takhir.animetitlesjava.requests.AnimeApiClient;

import java.util.List;

public class AnimeRepository {
    private static final String TAG = "AnimeRepository";

    private static AnimeRepository instance;
    private final AnimeApiClient mAnimeApiClient;
    private String mQuery;
    private int mPageLimit;
    private int mPageOffset;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<Anime>> mAnimeList = new MediatorLiveData<>();

    public static AnimeRepository getInstance() {
        if (instance == null) {
            instance = new AnimeRepository();
        }
        return instance;
    }

    private AnimeRepository() {
        mAnimeApiClient = AnimeApiClient.getInstance();
        initMediators();
    }

    private void initMediators() {
        LiveData<List<Anime>> animeListApiSource = mAnimeApiClient.getAnimeList();
        mAnimeList.addSource(animeListApiSource, new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> animeList) {
                if (animeList != null) {
                    mAnimeList.setValue(animeList);
                    doneQuery(animeList);
                } else {
                    //search database cache
                }
            }
        });
    }

    public MutableLiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }

    private void doneQuery(List<Anime> list) {
        if (list != null) {
            if (list.size() % 20 != 0) {
                mIsQueryExhausted.setValue(true);
            }
        } else {
            mIsQueryExhausted.setValue(true);
        }
    }

    public LiveData<List<Anime>> getAnimeList() {
        return mAnimeList;
    }

    public LiveData<List<Genres>> getAnimeGenres() {
        return mAnimeApiClient.getAnimeGenres();
    }

    public LiveData<Boolean> isGenresRequestTimedOut() {
        return mAnimeApiClient.isGenresRequestTimedOut();
    }

    public void searchAnimeListApi(String text, int pageLimit, int pageOffset) {
        mIsQueryExhausted.setValue(false);
        mAnimeApiClient.searchAnimeListApi(text, pageLimit, pageOffset);
    }

    public void searchAnimeGenresById(int id) {
        mAnimeApiClient.searchAnimeGenresById(id);
    }

    public void searchNextPage() {
        Log.d(TAG, "searchNextPage: " + mQuery +" "+ mPageLimit +" "+ mPageOffset);
        searchAnimeListApi(mQuery, mPageLimit, mPageOffset +20);
    }

    public void cancelRequest() {
        mAnimeApiClient.cancelRequest();
    }
}
