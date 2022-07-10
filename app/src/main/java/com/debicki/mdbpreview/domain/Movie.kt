package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.database.domain.MovieDB
import com.debicki.mdbpreview.network.domain.FullMovieDTO

data class Movie(
    val title: String,
    val plot: String,
    val imdbID: String,
    val imdbRating: String,
    val poster: String,
    val year: String,
    val type: String,
)

fun FullMovieDTO.toDomain() = Movie(Title, Plot, imdbID, imdbRating, Poster, Year, Type)

fun MovieDB.toDomain() = Movie(title, plot, imdbID, imdbRating, poster, year, type)

fun Movie.toDatabase() = MovieDB(imdbID, title, plot, imdbRating, year, type, poster)