package com.takhir.animetitlesjava.util;

import android.util.Log;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;

import java.util.List;

public class Testing {
    public static void printAnimeList(List<KitsuAnime> animeList, String TAG) {
        for (KitsuAnime anime : animeList) {
            Log.d(TAG, "list: " + anime.getLinks());
        }
    }
}
