package com.debicki.mdbpreview.database

import com.debicki.mdbpreview.domain.Movie
import com.debicki.mdbpreview.domain.toDatabase
import com.debicki.mdbpreview.domain.toDomain
import javax.inject.Inject

class FavoriteMoviesRepository @Inject constructor(private val favoritesMovieDao: FavoritesMovieDao) {

    suspend fun addMovie(movie: Movie) =
        favoritesMovieDao.insert(movie.toDatabase())

    suspend fun getAll() =
        favoritesMovieDao.getAll().map { it.toDomain() }
}