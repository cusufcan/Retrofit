package com.mercan.retrofitmvvm.data.remote

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieService {
    @GET("movie/{route}")
    suspend fun getMovieList(
        @Header("Authorization") token: String = Constants.API_KEY,
        @Path("route") apiRoute: String,
    ): Response<MovieList>
}