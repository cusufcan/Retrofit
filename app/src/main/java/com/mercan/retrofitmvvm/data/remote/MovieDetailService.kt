package com.mercan.retrofitmvvm.data.remote

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.detail.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieDetailService {
    @GET("movie/{id}")
    suspend fun getMovieDetailById(
        @Header("Authorization") token: String = Constants.API_KEY,
        @Path("id") id: Long,
    ): Response<MovieDetail>
}