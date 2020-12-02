package com.takhir.animetitlesjava.repositories;

import androidx.lifecycle.LiveData;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;
import com.takhir.animetitlesjava.models.shikimori.ShikimoriAnime;
import com.takhir.animetitlesjava.requests.AnimeApiClient;

import java.util.List;

public class AnimeRepository {
    private static AnimeRepository instance;
    private final AnimeApiClient mAnimeApiClient;
    private String mQuery;
    private int mPageLimit;
    private int mPageOffset;

    private AnimeRepository() {
        mAnimeApiClient = AnimeApiClient.getInstance();
    }

    public static AnimeRepository getInstance() {
        if (instance == null) {
            instance = new AnimeRepository();
        }
        return instance;
    }

    public LiveData<List<KitsuAnime>> getAnimeList() {
        return mAnimeApiClient.getAnimeList();
    }

    public void searchAnimeListApi(String text, int pageLimit, int pageOffset) {
        mQuery = text;
        mPageLimit = pageLimit;
        mPageOffset = pageOffset + 5;
        mAnimeApiClient.searchAnimeListApi(text, pageLimit, pageOffset);
    }

    public void searchNextPage() {
        searchAnimeListApi(mQuery, mPageLimit, mPageOffset);
    }

    public void cancelRequest() {
        mAnimeApiClient.cancelRequest();
    }
}
