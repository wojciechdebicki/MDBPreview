package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.NotInterestedMoviesRepository
import com.debicki.mdbpreview.network.MovieNetworkRepository
import com.debicki.mdbpreview.network.Response
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val movieNetworkRepository: MovieNetworkRepository,
    private val notInterestedMoviesRepository: NotInterestedMoviesRepository
) {

    suspend fun execute(query: String): Response<List<MovieDescription>> {

        return when (val fetched = movieNetworkRepository.fetch(query)) {
            is Response.Error -> Response.Error(fetched.error)
            is Response.Success -> {
                val filtered = fetched.value.filter { !notInterestedMoviesRepository.isBlocked(it.imdbID) }
                Response.Success(filtered.map { it.toDomain() })
            }
        }
    }
}