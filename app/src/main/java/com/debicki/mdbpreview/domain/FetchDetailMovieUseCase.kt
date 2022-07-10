package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.FavoriteMoviesRepository
import com.debicki.mdbpreview.database.MovieDatabaseRepository
import com.debicki.mdbpreview.database.NotInterestedMoviesRepository
import com.debicki.mdbpreview.network.MovieNetworkRepository
import javax.inject.Inject

data class FetchedDetail(val movie: Movie, val isFavorite: Boolean, val notInterested: Boolean)

class FetchDetailMovieUseCase @Inject constructor(
    private val movieNetworkRepository: MovieNetworkRepository,
    private val movieDatabaseRepository: MovieDatabaseRepository,
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
    private val notInterestedMoviesRepository: NotInterestedMoviesRepository,
) {
    suspend fun execute(movieId: String): FetchedDetail {
        var fetched = movieDatabaseRepository.getMovie(movieId)?.toDomain()
        if (fetched == null) {
            fetched = movieNetworkRepository.getDetails(movieId).toDomain()
            movieDatabaseRepository.add(fetched)
        }

        val isFavorite = favoriteMoviesRepository.isFavorite(fetched.imdbID)
        val isBlocked = notInterestedMoviesRepository.isBlocked(fetched.imdbID)

        return FetchedDetail(fetched, isFavorite, isBlocked)
    }
}