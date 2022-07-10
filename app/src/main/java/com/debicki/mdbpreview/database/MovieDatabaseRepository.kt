package com.debicki.mdbpreview.database

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.toDatabase
import com.debicki.mdbpreview.domain.toDomain
import javax.inject.Inject

class MovieDatabaseRepository @Inject constructor(private val moviesDao: MoviesDao) {

    suspend fun getAll() =
        moviesDao.getAll().map { it.toDomain() }

    suspend fun getAll(ids: List<String>) =
        moviesDao.getAll(ids).map { it.toDomain() }

    suspend fun add(movie: Movie) = moviesDao.insert(movie.toDatabase())

    suspend fun getMovie(imdbId: String) = moviesDao.fetch(imdbId)?.toDomain()
}