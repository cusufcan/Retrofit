package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercan.retrofitmvvm.data.model.detail.trailers.TrailerList
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieTrailersRepository
import kotlinx.coroutines.launch

class MovieTrailersViewModel : ViewModel() {
    private val movieTrailersRepository =
        MovieTrailersRepository(RetrofitInstance.movieTrailersService)

    val movieTrailers = MutableLiveData<TrailerList>()
    val movieTrailersLoading = MutableLiveData<Boolean>()

    private val errorMessage = MutableLiveData<String>()

    fun getMovieTrailersById(id: Long) = viewModelScope.launch {
        movieTrailersLoading.postValue(true)
        val response = movieTrailersRepository.getMovieTrailersById(id)
        if (response.isSuccessful) {
            val pureData = response.body()
            val trailers = pureData?.results?.filter { it.type == "Trailer" }
            val trailerList = TrailerList(
                pureData?.id ?: 0,
                trailers ?: emptyList()
            )
            movieTrailers.postValue(trailerList)
        } else {
            errorMessage.postValue(response.message())
        }
        movieTrailersLoading.postValue(false)
    }
}