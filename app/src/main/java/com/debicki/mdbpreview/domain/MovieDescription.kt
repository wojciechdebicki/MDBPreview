package com.debicki.mdbpreview.domain

import com.debicki.mdbpreview.network.domain.SearchMovieDTO

data class MovieDescription(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
)

fun SearchMovieDTO.toDomain() = MovieDescription(Title, Year, imdbID, Type, Poster)