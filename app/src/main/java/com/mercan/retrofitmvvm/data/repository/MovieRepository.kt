package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.remote.MovieService

class MovieRepository(private val movieService: MovieService) {
    suspend fun getPopularMovies() =
        movieService.getMovieList(apiRoute = Constants.POPULAR)

    suspend fun getNowPlayingMovies() =
        movieService.getMovieList(apiRoute = Constants.NOW_PLAYING)

    suspend fun getTopRatedMovies() =
        movieService.getMovieList(apiRoute = Constants.TOP_RATED)

    suspend fun getUpcomingMovies() =
        movieService.getMovieList(apiRoute = Constants.UPCOMING)

    suspend fun getGenres() = movieService.getGenres()
}