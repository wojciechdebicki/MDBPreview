package com.debicki.mdbpreview.network

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.MovieDescription
import com.debicki.mdbpreview.domain.toDomain
import javax.inject.Inject

sealed class Response<T> {
    data class Success<T>(val value: T) : Response<T>()
    data class Error<T>(val error: String) : Response<T>()
}

class MovieNetworkRepository @Inject constructor(private val omdbService: OMDBService) {

    suspend fun fetch(query: String, page: Int = 1): Response<List<MovieDescription>> {
        val fetched = omdbService.search(query, page)

        return fetched.Search?.let { list ->
            Response.Success(list.map { it.toDomain() })
        } ?: Response.Error(fetched.Error!!)
    }

    suspend fun getDetails(imdb: String): Movie =
        omdbService.details(imdb).toDomain()
}