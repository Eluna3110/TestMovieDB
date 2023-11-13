package com.software.testmoviedb.data.server

import com.software.testmoviedb.domain.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @GET("movie/popular")
    suspend fun getListMoviePopular(@Query("api_key") api_key: String?,
                            @Query("language") language: String?,
                            @Query("page") page: Int?) : Movie

    @GET("movie/top_rated")
    suspend fun getListMovieTopRated(@Query("api_key") api_key: String?,
                             @Query("language") language: String?,
                             @Query("page") page: Int?) : Movie
}