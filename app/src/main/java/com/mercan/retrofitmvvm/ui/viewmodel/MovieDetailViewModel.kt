package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.retrofitmvvm.data.model.detail.MovieDetail
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val movieDetailRepository = MovieDetailRepository(RetrofitInstance.movieDetailService)

    val movieDetail = MutableLiveData<MovieDetail>()
    val movieDetailLoading = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String>()

    fun getMovieById(id: Long) = viewModelScope.launch {
        movieDetailLoading.postValue(true)
        val response = movieDetailRepository.getMovieDetailById(id)
        if (response.isSuccessful) {
            movieDetail.postValue(response.body())
        } else {
            errorMessage.postValue("Error: ${response.code()}")
        }
        movieDetailLoading.postValue(false)
    }
}