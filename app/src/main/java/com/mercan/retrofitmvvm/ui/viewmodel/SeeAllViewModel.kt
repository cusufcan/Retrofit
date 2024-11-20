package com.mercan.retrofitmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.paging.MoviesPagingSource
import com.mercan.retrofitmvvm.data.remote.RetrofitInstance
import com.mercan.retrofitmvvm.data.repository.MovieRepository

class SeeAllViewModel : ViewModel() {
    private val movieRepository = MovieRepository(RetrofitInstance.movieService)

    val pagedNowPlayingMovieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(movieRepository, Constants.NOW_PLAYING)
    }.flow.cachedIn(viewModelScope)

    val pagedPopularMovieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(movieRepository, Constants.POPULAR)
    }.flow.cachedIn(viewModelScope)

    val pagedTopRatedMovieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(movieRepository, Constants.TOP_RATED)
    }.flow.cachedIn(viewModelScope)

    val pagedUpcomingMovieList = Pager(PagingConfig(1)) {
        MoviesPagingSource(movieRepository, Constants.UPCOMING)
    }.flow.cachedIn(viewModelScope)
}