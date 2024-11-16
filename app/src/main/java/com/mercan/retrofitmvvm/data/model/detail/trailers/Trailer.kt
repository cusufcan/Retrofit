package com.mercan.retrofitmvvm.data.model.detail.trailers

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    val id: String,
)
