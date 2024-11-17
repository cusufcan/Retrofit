package com.mercan.retrofitmvvm.data.model.credits

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("id") val id: Long,
    @SerializedName("cast") val cast: List<Cast>,
    @SerializedName("crew") val crew: List<Crew>,
)