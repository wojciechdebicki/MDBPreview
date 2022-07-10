package com.debicki.mdbpreview.network

import com.debicki.mdbpreview.network.domain.FullMovieDTO
import com.debicki.mdbpreview.network.domain.SearchDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBService {
    @GET(".")
    suspend fun search(
        @Query("s") search: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apikey: String = "2f43b5ba"
    ): SearchDTO

    @GET(".")
    suspend fun details(
        @Query("i") imdbId: String,
        @Query("apikey") apikey: String = "2f43b5ba"
    ): FullMovieDTO
}