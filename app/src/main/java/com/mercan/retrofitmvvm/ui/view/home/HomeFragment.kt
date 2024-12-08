package com.mercan.retrofitmvvm.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.databinding.FragmentHomeBinding
import com.mercan.retrofitmvvm.ui.adapter.homeslider.HomeSliderAdapter
import com.mercan.retrofitmvvm.ui.adapter.homeverticalcard.HomeVerticalCardAdapter
import com.mercan.retrofitmvvm.ui.view.main.MainActivity
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel
import com.mercan.retrofitmvvm.utils.setCompositeScroll
import com.mercan.retrofitmvvm.utils.startAutoScroll

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var homeNowPlayingAdapter: HomeSliderAdapter
    private lateinit var homePopularMoviesAdapter: HomeVerticalCardAdapter
    private lateinit var homeTopRatedMoviesAdapter: HomeVerticalCardAdapter
    private lateinit var homeUpcomingMoviesAdapter: HomeVerticalCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.binding.appBottomNavigationView.visibility = View.VISIBLE

        fetchData()
        setViewPager()
        setSeeAllOnClicks()

        return binding.root
    }

    private fun fetchData() {
        movieViewModel.fetchGenres()
        movieViewModel.genres.observe(viewLifecycleOwner) { genres ->
            fetchNowPlaying(genres)
            fetchPopularMovies(genres)
            fetchTopRatedMovies(genres)
            fetchUpcomingMovies(genres)
        }
    }

    private fun fetchNowPlaying(genres: GenreList) {
        movieViewModel.fetchNowPlayingMovies()
        movieViewModel.nowPlayingLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.nowPlayingProgressBar.visibility = View.GONE
                binding.nowPlayingViewPager.visibility = View.VISIBLE

                homeNowPlayingAdapter = HomeSliderAdapter(
                    parentFragmentManager,
                    movieViewModel.nowPlayingMovies.value!!,
                    genres,
                )
                binding.nowPlayingViewPager.adapter = homeNowPlayingAdapter
            }
        }
    }

    private fun fetchPopularMovies(genres: GenreList) {
        movieViewModel.fetchPopularMovies()
        movieViewModel.popularLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                homePopularMoviesAdapter = HomeVerticalCardAdapter(
                    parentFragmentManager,
                    movieViewModel.popularMovies.value!!,
                    genres,
                )
                binding.popularMoviesRecyclerView.adapter = homePopularMoviesAdapter

                binding.popularMoviesProgressBar.visibility = View.GONE
                binding.popularMoviesRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun fetchTopRatedMovies(genres: GenreList) {
        movieViewModel.fetchTopRatedMovies()
        movieViewModel.topRatedLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                homeTopRatedMoviesAdapter = HomeVerticalCardAdapter(
                    parentFragmentManager,
                    movieViewModel.topRatedMovies.value!!,
                    genres,
                )
                binding.topRatedMoviesRecyclerView.adapter = homeTopRatedMoviesAdapter

                binding.topRatedMoviesProgressBar.visibility = View.GONE
                binding.topRatedMoviesRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun fetchUpcomingMovies(genres: GenreList) {
        movieViewModel.fetchUpcomingMovies()
        movieViewModel.upcomingLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                homeUpcomingMoviesAdapter = HomeVerticalCardAdapter(
                    parentFragmentManager,
                    movieViewModel.upcomingMovies.value!!,
                    genres,
                )
                binding.upcomingMoviesRecyclerView.adapter = homeUpcomingMoviesAdapter

                binding.upcomingMoviesProgressBar.visibility = View.GONE
                binding.upcomingMoviesRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun setViewPager() {
        binding.nowPlayingViewPager.setCompositeScroll()
        binding.nowPlayingViewPager.startAutoScroll(viewLifecycleOwner, Constants.DURATION_NORMAL)
        binding.nowPlayingViewPager.offscreenPageLimit = 3
    }

    private fun setSeeAllOnClicks() {
        val navController = findNavController()

        binding.nowPlayingSeeAllButton.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                    Constants.NOW_PLAYING
                )
            )
        }

        binding.popularMoviesSeeAllButton.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                    Constants.POPULAR
                )
            )
        }

        binding.topRatedMoviesSeeAllButton.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                    Constants.TOP_RATED
                )
            )
        }

        binding.upcomingMoviesSeeAllButton.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                    Constants.UPCOMING
                )
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}