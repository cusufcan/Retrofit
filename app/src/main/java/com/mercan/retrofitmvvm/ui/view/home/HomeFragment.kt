package com.mercan.retrofitmvvm.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.databinding.FragmentHomeBinding
import com.mercan.retrofitmvvm.ui.adapter.homeslider.HomeSliderAdapter
import com.mercan.retrofitmvvm.ui.adapter.homeverticalcard.HomeVerticalCardAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel
import com.mercan.retrofitmvvm.utils.setCompositeScroll
import com.mercan.retrofitmvvm.utils.startAutoScroll

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var homeNowPlayingAdapter: HomeSliderAdapter
    private lateinit var homePopularMoviesAdapter: HomeVerticalCardAdapter
    private lateinit var homeTopRatedMoviesAdapter: HomeVerticalCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        fetchData()
        setViewPager()

        return binding.root
    }

    private fun fetchData() {
        movieViewModel.fetchGenres()
        movieViewModel.genres.observe(viewLifecycleOwner) { genres ->
            fetchNowPlaying(genres)
            fetchPopularMovies(genres)
            fetchTopRatedMovies(genres)
        }
    }

    private fun fetchNowPlaying(genres: GenreList) {
        movieViewModel.fetchNowPlayingMovies()
        movieViewModel.nowPlayingLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.nowPlayingProgressBar.visibility = View.GONE
                binding.nowPlayingViewPager.visibility = View.VISIBLE

                homeNowPlayingAdapter = HomeSliderAdapter(
                    movieViewModel.nowPlayingMovies.value!!, genres
                )
                binding.nowPlayingViewPager.adapter = homeNowPlayingAdapter
            }
        }
    }

    private fun fetchPopularMovies(genres: GenreList) {
        movieViewModel.fetchPopularMovies()
        movieViewModel.popularLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.popularMoviesProgressBar.visibility = View.GONE
                binding.popularMoviesRecyclerView.visibility = View.VISIBLE

                homePopularMoviesAdapter = HomeVerticalCardAdapter(
                    movieViewModel.popularMovies.value!!, genres
                )
                binding.popularMoviesRecyclerView.adapter = homePopularMoviesAdapter
            }
        }
    }

    private fun fetchTopRatedMovies(genres: GenreList) {
        movieViewModel.fetchTopRatedMovies()
        movieViewModel.topRatedLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.topRatedMoviesProgressBar.visibility = View.GONE
                binding.topRatedMoviesRecyclerView.visibility = View.VISIBLE

                homeTopRatedMoviesAdapter = HomeVerticalCardAdapter(
                    movieViewModel.topRatedMovies.value!!, genres
                )
                binding.topRatedMoviesRecyclerView.adapter = homeTopRatedMoviesAdapter
            }
        }
    }

    private fun setViewPager() {
        binding.nowPlayingViewPager.setCompositeScroll()
        binding.nowPlayingViewPager.startAutoScroll(viewLifecycleOwner, Constants.DURATION_NORMAL)
        binding.nowPlayingViewPager.offscreenPageLimit = 3
    }
}