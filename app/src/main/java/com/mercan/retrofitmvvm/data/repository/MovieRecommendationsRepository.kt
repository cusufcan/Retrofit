package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.data.remote.MovieRecommendationsService

class MovieRecommendationsRepository(
    private val movieRecommendationsService: MovieRecommendationsService
) {
    suspend fun getRecommendationsById(id: Long) =
        movieRecommendationsService.getRecommendationsById(id = id)
}