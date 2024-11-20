package com.mercan.retrofitmvvm.data.repository

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.remote.MovieService

class MovieRepository(private val movieService: MovieService) {
    suspend fun getPopularMovies(page: Int = 1) =
        movieService.getMovieList(
            apiRoute = Constants.POPULAR,
            page = page,
        )

    suspend fun getNowPlayingMovies(page: Int = 1) =
        movieService.getMovieList(
            apiRoute = Constants.NOW_PLAYING,
            page = page,
        )

    suspend fun getTopRatedMovies(page: Int = 1) =
        movieService.getMovieList(
            apiRoute = Constants.TOP_RATED,
            page = page,
        )

    suspend fun getUpcomingMovies(page: Int = 1) =
        movieService.getMovieList(
            apiRoute = Constants.UPCOMING,
            page = page,
        )

    suspend fun getGenres() = movieService.getGenres()
}