package com.debicki.mdbpreview.network

import com.debicki.mdbpreview.network.domain.FullMovieDTO
import com.debicki.mdbpreview.network.domain.SearchMovieDTO
import javax.inject.Inject

class MovieNetworkRepository @Inject constructor(private val omdbService: OMDBService) {

    suspend fun fetch(query: String, page: Int = 1): Response<List<SearchMovieDTO>> {
        val fetched = omdbService.search(query, page)
        return fetched.Search?.let { list ->
            Response.Success(list)
        } ?: Response.Error(fetched.Error!!)
    }

    suspend fun getDetails(imdb: String): FullMovieDTO = omdbService.details(imdb)
}