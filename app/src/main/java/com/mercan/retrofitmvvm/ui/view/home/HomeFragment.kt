package com.mercan.retrofitmvvm.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.databinding.FragmentHomeBinding
import com.mercan.retrofitmvvm.ui.adapter.homeslider.HomeSliderAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel
import com.mercan.retrofitmvvm.utils.setCompositeScroll
import com.mercan.retrofitmvvm.utils.startAutoScroll

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var homeSliderAdapter: HomeSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setNowPlayingList()
        setViewPager()

        return binding.root
    }

    private fun setNowPlayingList() {
        movieViewModel.fetchGenres()
        movieViewModel.genres.observe(viewLifecycleOwner) { genres ->
            movieViewModel.fetchNowPlayingMovies()
            movieViewModel.nowPlayingLoading.observe(viewLifecycleOwner) { isLoading ->
                if (!isLoading) {
                    binding.nowPlayingProgressBar.visibility = View.GONE
                    binding.nowPlayingViewPager.visibility = View.VISIBLE

                    homeSliderAdapter = HomeSliderAdapter(
                        movieViewModel.nowPlayingMovies.value!!, genres
                    )
                    binding.nowPlayingViewPager.adapter = homeSliderAdapter
                }
            }
        }
    }

    private fun setViewPager() {
        binding.nowPlayingViewPager.setCompositeScroll()
        binding.nowPlayingViewPager.startAutoScroll(viewLifecycleOwner, Constants.DURATION_NORMAL)
        binding.nowPlayingViewPager.offscreenPageLimit = 3
    }
}