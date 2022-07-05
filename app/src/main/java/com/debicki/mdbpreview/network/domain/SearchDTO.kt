package com.debicki.mdbpreview.network.domain

data class SearchDTO(
    val Search: List<MovieDTO>,
    val totalResults: Int
)
