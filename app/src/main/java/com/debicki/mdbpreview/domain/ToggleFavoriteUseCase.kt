package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.FavoriteMoviesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoriteMoviesRepository: FavoriteMoviesRepository,
) {

    suspend fun execute(isFavorite: Boolean, movie: Movie) {
        if (isFavorite) {
            favoriteMoviesRepository.addMovie(movie)
        } else {
            favoriteMoviesRepository.remove(movie)
        }
    }
}