package com.debicki.mdbpreview

import retrofit2.http.GET
import retrofit2.http.Query

data class Search(val Search: List<Movie>, val totalResults: Int)
data class Movie(private val Title: String)

interface OMDBService {
    @GET(".")
    suspend fun search(@Query("s") search: String, @Query("apikey") apikey: String = "2f43b5ba"): Search
}