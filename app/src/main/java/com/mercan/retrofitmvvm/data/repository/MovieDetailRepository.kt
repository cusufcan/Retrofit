package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.data.remote.MovieDetailService

class MovieDetailRepository(private val movieDetailService: MovieDetailService) {
    suspend fun getMovieDetailById(id: Long) =
        movieDetailService.getMovieDetailById(id = id)
}