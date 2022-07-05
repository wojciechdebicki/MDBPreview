package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.network.domain.MovieDTO

data class Movie(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
)

fun MovieDTO.toDomain() = Movie(Title, Year, imdbID, Type, Poster)
