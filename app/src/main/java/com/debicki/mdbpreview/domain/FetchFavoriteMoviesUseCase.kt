package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.FavoriteMoviesRepository
import com.debicki.mdbpreview.database.MovieDatabaseRepository
import javax.inject.Inject

class FetchFavoriteMoviesUseCase @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
    private val moviesRepository: MovieDatabaseRepository
) {

    suspend fun execute(): List<Movie> {
        val favorites = favoriteMoviesRepository.getAll()
        return moviesRepository.getAll(favorites).map { it.toDomain() }
    }
}