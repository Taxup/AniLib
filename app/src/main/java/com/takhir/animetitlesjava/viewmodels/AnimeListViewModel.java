package com.takhir.animetitlesjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;
import com.takhir.animetitlesjava.models.shikimori.ShikimoriAnime;
import com.takhir.animetitlesjava.repositories.AnimeRepository;

import java.util.List;

public class AnimeListViewModel extends ViewModel {
    private final AnimeRepository mAnimeRepository;
    private boolean mPerformingQuery;

    public AnimeListViewModel() {
        mAnimeRepository = AnimeRepository.getInstance();
        mPerformingQuery = true;
    }

    public boolean isPerformingQuery() {
        return mPerformingQuery;
    }

    public void setPerformingQuery(boolean mPerformingQuery) {
        this.mPerformingQuery = mPerformingQuery;
    }

    public LiveData<List<KitsuAnime>> getAnimeList() {
        return mAnimeRepository.getAnimeList();
    }

    public void searchAnimeListApi(String text, int pageLimit, int pageOffset) {
        mAnimeRepository.searchAnimeListApi(text, pageLimit, pageOffset);
    }

    public boolean onBackPressed() {
        if (mPerformingQuery) {
            mAnimeRepository.cancelRequest();
            mPerformingQuery = false;
            return false;
        } else {
            return true;
        }
    }

    public void searchNextPage() {
        if (!mPerformingQuery) {
            mAnimeRepository.searchNextPage();
        }
    }
}
