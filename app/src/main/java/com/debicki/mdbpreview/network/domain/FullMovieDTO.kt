package com.debicki.mdbpreview.network.domain

data class FullMovieDTO(
    val Title: String,
    val Plot: String,
    val imdbID: String,
    val imdbRating: String,
    val Poster: String,
    val Year: String,
    val Type: String,
)