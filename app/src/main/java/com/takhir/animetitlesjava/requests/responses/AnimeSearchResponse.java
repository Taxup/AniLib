package com.takhir.animetitlesjava.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.takhir.animetitlesjava.models.kitsu.Anime;
import com.takhir.animetitlesjava.models.kitsu.Links;

import java.util.List;

public class AnimeSearchResponse {
    @SerializedName("data")
    @Expose
    private List<Anime> anime;

    @SerializedName("links")
    @Expose
    private Links links;

    public List<Anime> getAnime() {
        return anime;
    }

    public Links getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "SearchAnimeResponse{" +
                "anime=" + anime +
                ", links=" + links +
                '}';
    }
}
