package com.debicki.mdbpreview.database

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.toDatabase
import javax.inject.Inject

class MovieDatabaseRepository @Inject constructor(private val moviesDao: MoviesDao) {
    suspend fun getAll(ids: List<String>) = moviesDao.getAll(ids)

    suspend fun add(movie: Movie) = moviesDao.insert(movie.toDatabase())

    suspend fun getMovie(imdbId: String) = moviesDao.fetch(imdbId)
}