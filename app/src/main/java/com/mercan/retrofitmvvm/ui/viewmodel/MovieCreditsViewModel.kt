package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.retrofitmvvm.data.model.credits.Credits
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieCreditsRepository
import kotlinx.coroutines.launch

class MovieCreditsViewModel : ViewModel() {
    private val movieCreditsRepository =
        MovieCreditsRepository(RetrofitInstance.movieCreditsService)

    val movieCredits = MutableLiveData<Credits>()
    val movieCreditsLoading = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()

    fun getMovieCreditsById(id: Long) = viewModelScope.launch {
        movieCreditsLoading.postValue(true)
        val response = movieCreditsRepository.getMovieCreditsById(id)
        if (response.isSuccessful) {
            movieCredits.postValue(response.body())
        } else {
            errorMessage.postValue(response.message())
        }
        movieCreditsLoading.postValue(false)
    }
}