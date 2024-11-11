package com.mercan.retrofitmvvm.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.mercan.retrofitmvvm.databinding.FragmentHomeBinding
import com.mercan.retrofitmvvm.ui.adapter.homeslider.HomeSliderAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel
import kotlin.math.abs

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
        movieViewModel.fetchNowPlayingMovies()
        movieViewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            println(it)
            homeSliderAdapter = HomeSliderAdapter(it)
            binding.nowPlayingViewPager.adapter = homeSliderAdapter
        }
    }

    private fun setViewPager() {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.80f + r * 0.20f
        }
        binding.nowPlayingViewPager.setPageTransformer(compositePageTransformer)
    }
}