package com.mercan.retrofitmvvm.core

import com.mercan.retrofitmvvm.BuildConfig

object Constants {
    const val BASE_URL: String = BuildConfig.BASE_URL
    const val API_KEY: String = BuildConfig.API_KEY

    const val DURATION_NORMAL = 5000L

    const val NORMAL_TAG = "1"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val NOW_PLAYING = "now_playing"
    const val POPULAR = "popular"
    const val TOP_RATED = "top_rated"
    const val UPCOMING = "upcoming"
}