package com.debicki.mdbpreview.network.domain

data class SearchMovieDTO(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String,
)
