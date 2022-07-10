package com.debicki.mdbpreview.network.domain

data class SearchDTO(
    val Search: List<SearchMovieDTO>,
    val totalResults: Int
)
