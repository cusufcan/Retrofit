package com.mercan.retrofitmvvm.data.remote

import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.credits.Credits
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieCreditsService {
    @GET("movie/{id}/credits")
    suspend fun getMovieCreditsById(
        @Header("Authorization") token: String = Constants.API_KEY,
        @Path("id") id: Long,
    ): Response<Credits>
}