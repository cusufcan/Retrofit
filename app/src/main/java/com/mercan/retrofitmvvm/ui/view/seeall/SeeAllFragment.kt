package com.mercan.retrofitmvvm.ui.view.seeall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mercan.retrofitmvvm.R
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.databinding.FragmentSeeAllBinding
import com.mercan.retrofitmvvm.ui.adapter.seeall.SeeAllAdapter
import com.mercan.retrofitmvvm.ui.view.main.MainActivity
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel
import com.mercan.retrofitmvvm.ui.viewmodel.SeeAllViewModel
import kotlinx.coroutines.launch

class SeeAllFragment : Fragment() {
    private var _binding: FragmentSeeAllBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels()
    private val seeAllViewModel: SeeAllViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.binding.appBottomNavigationView.visibility = View.VISIBLE

        val from = SeeAllFragmentArgs.fromBundle(requireArguments()).from

        setOnClickListeners()

        setTitle(from)
        setAdapters(from)

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.toolBarBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTitle(from: String) {
        binding.toolBarTitleTextView.text = when (from) {
            Constants.NOW_PLAYING -> getString(R.string.now_playing)
            Constants.POPULAR -> getString(R.string.popular_movies)
            Constants.TOP_RATED -> getString(R.string.top_rated_movies)
            Constants.UPCOMING -> getString(R.string.upcoming_movies)
            else -> getString(R.string.upcoming_movies)
        }
    }

    private fun setAdapters(from: String) {
        val adapter = SeeAllAdapter(
            parentFragmentManager,
            movieViewModel.genres.value,
        )
        lifecycleScope.launch {
            when (from) {
                Constants.NOW_PLAYING -> seeAllViewModel.pagedNowPlayingMovieList.collect {
                    adapter.submitData(it)
                }

                Constants.POPULAR -> seeAllViewModel.pagedPopularMovieList.collect {
                    adapter.submitData(it)
                }

                Constants.TOP_RATED -> seeAllViewModel.pagedTopRatedMovieList.collect {
                    adapter.submitData(it)
                }

                Constants.UPCOMING -> seeAllViewModel.pagedUpcomingMovieList.collect {
                    adapter.submitData(it)
                }
            }
        }

        binding.seeAllRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}