package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.data.remote.MovieCreditsService

class MovieCreditsRepository(private val movieCreditsService: MovieCreditsService) {
    suspend fun getMovieCreditsById(id: Long) =
        movieCreditsService.getMovieCreditsById(id = id)
}