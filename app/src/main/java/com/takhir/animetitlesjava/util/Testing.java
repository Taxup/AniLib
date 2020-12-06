package com.takhir.animetitlesjava.util;

import android.util.Log;
import com.takhir.animetitlesjava.models.kitsu.Anime;

import java.util.List;

public class Testing {
    public static void printAnimeList(List<Anime> animeList, String TAG) {
        for (Anime anime : animeList) {
            Log.d(TAG, "list: " + anime.getLinks());
        }
    }
}
