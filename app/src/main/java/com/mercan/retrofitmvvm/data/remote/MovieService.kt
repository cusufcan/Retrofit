package com.mercan.retrofitmvvm.data.remote

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{route}")
    suspend fun getMovieList(
        @Header("Authorization") token: String = Constants.API_KEY,
        @Path("route") apiRoute: String,
        @Query("page") page: Int = 1,
    ): Response<MovieList>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Header("Authorization") token: String = Constants.API_KEY,
    ): Response<GenreList>
}