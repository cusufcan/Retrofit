package com.mercan.retrofitmvvm.ui.view.seeall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mercan.retrofitmvvm.R
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.FragmentSeeAllBinding
import com.mercan.retrofitmvvm.ui.adapter.seeall.SeeAllAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel

class SeeAllFragment : Fragment() {
    private var _binding: FragmentSeeAllBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)

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

        binding.seeAllRecyclerView.adapter = when (from) {
            Constants.NOW_PLAYING -> SeeAllAdapter(
                movieViewModel.nowPlayingMovies.value ?: MovieList.empty(),
                movieViewModel.genres.value ?: GenreList.empty()
            )

            Constants.POPULAR -> SeeAllAdapter(
                movieViewModel.popularMovies.value ?: MovieList.empty(),
                movieViewModel.genres.value ?: GenreList.empty()
            )

            Constants.TOP_RATED -> SeeAllAdapter(
                movieViewModel.topRatedMovies.value ?: MovieList.empty(),
                movieViewModel.genres.value ?: GenreList.empty()
            )

            Constants.UPCOMING -> SeeAllAdapter(
                movieViewModel.upcomingMovies.value ?: MovieList.empty(),
                movieViewModel.genres.value ?: GenreList.empty()
            )

            else -> SeeAllAdapter(
                movieViewModel.upcomingMovies.value ?: MovieList.empty(),
                movieViewModel.genres.value ?: GenreList.empty()
            )
        }
    }
}