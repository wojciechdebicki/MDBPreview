package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.NotInterestedMoviesRepository
import javax.inject.Inject

class ToggleNotInterestedUseCase @Inject constructor(
    private val notInterestedMoviesRepository: NotInterestedMoviesRepository
) {

    suspend fun execute(isBlocked: Boolean, movie: Movie) {
        if (isBlocked) {
            notInterestedMoviesRepository.addMovie(movie)
        } else {
            notInterestedMoviesRepository.remove(movie)
        }
    }
}