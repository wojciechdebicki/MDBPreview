package com.debicki.mdbpreview

import javax.inject.Inject

class MovieRepository @Inject constructor(private val omdbService: OMDBService) {

    suspend fun fetch() = omdbService.search("blade")
}