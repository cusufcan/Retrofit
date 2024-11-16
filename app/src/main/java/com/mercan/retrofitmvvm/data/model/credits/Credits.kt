package com.mercan.retrofitmvvm.data.model.credits

data class Credits(
    val id: Long,
    val cast: List<Cast>,
    val crew: List<Crew>,
)