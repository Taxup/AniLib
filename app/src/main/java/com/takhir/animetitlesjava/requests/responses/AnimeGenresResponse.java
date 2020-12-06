package com.takhir.animetitlesjava.requests.responses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.takhir.animetitlesjava.models.kitsu.Genres;

import java.util.List;

public class AnimeGenresResponse {
    @SerializedName("data")
    @Expose
    private List<Genres> genres;

    public List<Genres> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "AnimeResponse{" +
                "genres=" + genres +
                '}';
    }
}

