package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.NotInterestedMoviesRepository
import javax.inject.Inject

class ClearNotInterestedUseCase @Inject constructor(
    private val notInterestedMoviesRepository: NotInterestedMoviesRepository
) {
    suspend fun execute() {
        notInterestedMoviesRepository.clear()
    }
}