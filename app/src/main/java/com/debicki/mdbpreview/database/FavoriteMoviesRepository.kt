package com.debicki.mdbpreview.database

import com.debicki.mdbpreview.database.domain.FavoriteMovieDB
import com.debicki.mdbpreview.domain.Movie
import javax.inject.Inject

class FavoriteMoviesRepository @Inject constructor(private val favoritesMovieDao: FavoritesMovieDao) {

    suspend fun addMovie(movie: Movie) =
        favoritesMovieDao.insert(FavoriteMovieDB(movie.imdbID))

    suspend fun getAll() =
        favoritesMovieDao.getAll().map { it.imdbID }

    suspend fun isFavorite(imdb: String) = favoritesMovieDao.count(imdb) > 0
}