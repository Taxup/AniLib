package com.takhir.animetitlesjava.requests;

import com.takhir.animetitlesjava.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.takhir.animetitlesjava.util.Constants.BASE_URL;

public class ServiceGenerator {

    private static final Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final AnimeAPI animeAPI = retrofit.create(AnimeAPI.class);

    public static AnimeAPI getAnimeAPI() {
        return animeAPI;
    }
}

