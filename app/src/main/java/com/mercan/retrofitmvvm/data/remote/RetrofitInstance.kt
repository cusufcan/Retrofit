package com.mercan.retrofitmvvm.data.remote

import com.mercan.retrofitmvvm.core.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val movieService: MovieService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

    val movieDetailService: MovieDetailService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDetailService::class.java)
    }

    val movieCreditsService: MovieCreditsService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieCreditsService::class.java)
    }

    val movieRecommendationsService: MovieRecommendationsService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieRecommendationsService::class.java)
    }

    val movieTrailersService: MovieTrailersService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieTrailersService::class.java)
    }
}