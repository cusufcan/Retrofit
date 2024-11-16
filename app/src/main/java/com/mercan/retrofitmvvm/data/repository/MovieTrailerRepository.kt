package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.data.remote.MovieTrailersService

class MovieTrailerRepository(private val movieTrailersService: MovieTrailersService) {
    suspend fun getMovieTrailersById(id: Long) =
        movieTrailersService.getMovieTrailersById(id = id)
}