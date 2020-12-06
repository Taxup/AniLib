package com.takhir.animetitlesjava.requests;

import com.takhir.animetitlesjava.requests.responses.AnimeGenresResponse;
import com.takhir.animetitlesjava.requests.responses.AnimeSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeAPI {
    @GET("anime")
    Call<AnimeSearchResponse> searchAnime(
            @Query("filter[text]") String filterText,
            @Query("page[limit]") int pageLimit,
            @Query("page[offset]") int pageOffset
    );

    @GET("anime/{id}/genres")
    Call<AnimeGenresResponse> getAnimeGenresById(@Path("id") int id);
}
