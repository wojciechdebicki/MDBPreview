package com.debicki.mdbpreview.network

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(private val omdbService: OMDBService) {

    suspend fun fetch(page: Int = 1): List<Movie> =
        omdbService.search("blade", page).Search.map { it.toDomain() }
}