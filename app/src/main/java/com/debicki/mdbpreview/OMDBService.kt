package com.debicki.mdbpreview

import retrofit2.http.GET
import retrofit2.http.Query

data class Search(val Search: List<Movie>, val totalResults: Int)

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String,
)

interface OMDBService {
    @GET(".")
    suspend fun search(
        @Query("s") search: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apikey: String = "2f43b5ba"
    ): Search
}