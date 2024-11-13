package com.mercan.retrofitmvvm.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("dates") val dates: Dates?,
    @SerializedName("page") val page: Long?,
    @SerializedName("results") val results: List<Movie>?,
    @SerializedName("total_pages") val totalPages: Long?,
    @SerializedName("total_results") val totalResults: Long?,
) {
    companion object {
        fun empty() = MovieList(null, null, null, null, null)
    }
}
