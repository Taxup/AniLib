package com.takhir.animetitlesjava.requests.responses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;

public class AnimeResponse {
    @SerializedName("data")
    @Expose
    private KitsuAnime anime;

    public KitsuAnime getAnime() {
        return anime;
    }

    @Override
    public String toString() {
        return "AnimeResponse{" +
                "anime=" + anime +
                '}';
    }
}

