package com.mercan.retrofitmvvm.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.Movie
import com.mercan.retrofitmvvm.data.repository.MovieRepository
import retrofit2.HttpException

class MoviesPagingSource(
    private val repository: MovieRepository,
    private val route: String,
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = when (route) {
                Constants.NOW_PLAYING -> repository.getNowPlayingMovies(currentPage)
                Constants.POPULAR -> repository.getPopularMovies(currentPage)
                Constants.TOP_RATED -> repository.getTopRatedMovies(currentPage)
                Constants.UPCOMING -> repository.getUpcomingMovies(currentPage)
                else -> repository.getNowPlayingMovies(currentPage)
            }
            val data = response.body()?.results
            val responseData = mutableListOf<Movie>()

            data?.let {
                responseData.addAll(it)
            }

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>) = null
}