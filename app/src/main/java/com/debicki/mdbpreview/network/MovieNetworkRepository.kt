package com.debicki.mdbpreview.network

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.MovieDescription
import com.debicki.mdbpreview.domain.toDomain
import javax.inject.Inject

class MovieNetworkRepository @Inject constructor(private val omdbService: OMDBService) {

    suspend fun fetch(query: String, page: Int = 1): List<MovieDescription> =
        omdbService.search(query, page).Search.map { it.toDomain() }

    suspend fun getDetails(imdb: String): Movie =
        omdbService.details(imdb).toDomain()
}