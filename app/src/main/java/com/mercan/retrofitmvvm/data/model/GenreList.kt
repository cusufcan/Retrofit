package com.mercan.retrofitmvvm.data.model

import com.google.gson.annotations.SerializedName

data class GenreList(
    @SerializedName("genres") val genres: List<Genre>
) {
    companion object {
        fun empty() = GenreList(emptyList())
    }
}
