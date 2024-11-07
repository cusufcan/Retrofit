package com.mercan.retrofitmvvm.data.model

data class MovieList(
    val dates: Dates?,
    val page: Long?,
    val results: List<Movie>?,
    val totalPages: Long?,
    val totalResults: Long?,
)
