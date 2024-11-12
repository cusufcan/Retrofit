package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository(RetrofitInstance.movieService)

    val nowPlayingMovies = MutableLiveData<MovieList>()
    val nowPlayingLoading = MutableLiveData<Boolean>()

    val popularMovies = MutableLiveData<MovieList>()
    val popularLoading = MutableLiveData<Boolean>()

    val topRatedMovies = MutableLiveData<MovieList>()
    val upcomingMovies = MutableLiveData<MovieList>()

    val genres = MutableLiveData<GenreList>()

    val errorMessage = MutableLiveData<String>()

    fun fetchNowPlayingMovies() = viewModelScope.launch {
        nowPlayingLoading.postValue(true)
        val response = movieRepository.getNowPlayingMovies()
        if (response.isSuccessful) {
            nowPlayingMovies.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
        nowPlayingLoading.postValue(false)
    }

    fun fetchPopularMovies() = viewModelScope.launch {
        popularLoading.postValue(true)
        val response = movieRepository.getPopularMovies()
        if (response.isSuccessful) {
            popularMovies.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
        popularLoading.postValue(false)
    }

    fun fetchTopRatedMovies() = viewModelScope.launch {
        val response = movieRepository.getTopRatedMovies()
        if (response.isSuccessful) {
            topRatedMovies.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
    }

    fun fetchUpcomingMovies() = viewModelScope.launch {
        val response = movieRepository.getUpcomingMovies()
        if (response.isSuccessful) {
            upcomingMovies.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
    }

    fun fetchGenres() = viewModelScope.launch {
        val response = movieRepository.getGenres()
        if (response.isSuccessful) {
            genres.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
    }
}