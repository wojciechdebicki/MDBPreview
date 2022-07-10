package com.debicki.mdbpreview.network.domain

data class SearchDTO(
    val Response: String,
    val Search: List<SearchMovieDTO>?,
    val totalResults: Int?,
    val Error: String?
)
