package com.takhir.animetitlesjava.requests.responses;

import com.google.gson.annotations.Expose;
import com.takhir.animetitlesjava.models.shikimori.ShikimoriAnime;

import java.util.List;

public class ShikimoriAnimeSearchResponse {
    @Expose
    private List<ShikimoriAnime> anime;

    public List<ShikimoriAnime> getAnime() {
        return anime;
    }

    @Override
    public String toString() {
        return "ShikimoriAnimeSearchResponse{" +
                "anime=" + anime +
                '}';
    }
}
