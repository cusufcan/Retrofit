package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieRecommendationsRepository
import kotlinx.coroutines.launch

class MovieRecommendationsViewModel : ViewModel() {
    private val movieRecommendationsRepository =
        MovieRecommendationsRepository(RetrofitInstance.movieRecommendationsService)

    val movieRecommendations = MutableLiveData<MovieList>()
    val movieRecommendationsLoading = MutableLiveData<Boolean>()

    private val errorMessage = MutableLiveData<String>()

    fun getRecommendationsById(id: Long) = viewModelScope.launch {
        movieRecommendationsLoading.postValue(true)
        val response = movieRecommendationsRepository.getRecommendationsById(id = id)
        if (response.isSuccessful) {
            movieRecommendations.postValue(response.body())
        } else {
            errorMessage.postValue(response.message())
        }
        movieRecommendationsLoading.postValue(false)
    }
}