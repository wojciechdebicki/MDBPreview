package com.debicki.mdbpreview.database

import com.debicki.mdbpreview.database.domain.NotInterestedMovieDB
import com.debicki.mdbpreview.domain.Movie
import javax.inject.Inject

class NotInterestedMoviesRepository @Inject constructor(private val notInterestedMovieDao: NotInterestedMovieDao) {

    suspend fun addMovie(movie: Movie) = notInterestedMovieDao.insert(NotInterestedMovieDB(movie.imdbID))

    suspend fun isBlocked(imdb: String) = notInterestedMovieDao.count(imdb) > 0

    suspend fun remove(movie: Movie) = notInterestedMovieDao.delete(NotInterestedMovieDB(movie.imdbID))
}