package com.takhir.animetitlesjava.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.takhir.animetitlesjava.models.kitsu.Genres;
import com.takhir.animetitlesjava.repositories.AnimeRepository;

import java.util.List;

public class AnimeViewModel extends ViewModel {
    private final AnimeRepository mAnimeRepository;
    private int mAnimeId;
    private boolean mDidRetrieveGenres;

    public AnimeViewModel() {
        this.mAnimeRepository = AnimeRepository.getInstance();
        mDidRetrieveGenres = false;
    }

    public int getAnimeId() {
        return mAnimeId;
    }

    public LiveData<List<Genres>> getAnimeGenres() {
        return mAnimeRepository.getAnimeGenres();
    }

    public LiveData<Boolean> isGenresRequestTimedOut() {
        return mAnimeRepository.isGenresRequestTimedOut();
    }

    public void searchAnimeGenresById(int id) {
        mAnimeId = id;
        mAnimeRepository.searchAnimeGenresById(id);
    }

    public boolean didRetrieveGenres() {
        return mDidRetrieveGenres;
    }

    public void setRetrievedGenres(boolean retrievedGenres) {
        this.mDidRetrieveGenres = retrievedGenres;
    }
}
