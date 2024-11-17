package com.mercan.retrofitmvvm.data.model.detail.trailers

import com.google.gson.annotations.SerializedName

data class TrailerList(
    @SerializedName("id") val id: Long,
    @SerializedName("results") val results: List<Trailer>,
)