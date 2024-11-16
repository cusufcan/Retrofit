package com.mercan.retrofitmvvm.ui.view.detail.trailers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mercan.retrofitmvvm.databinding.FragmentTrailersBinding
import com.mercan.retrofitmvvm.ui.adapter.detail.trailers.MovieTrailersAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieDetailViewModel
import com.mercan.retrofitmvvm.ui.viewmodel.MovieTrailersViewModel

class TrailersFragment : Fragment() {
    private var _binding: FragmentTrailersBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    private val movieTrailersViewModel: MovieTrailersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailersBinding.inflate(inflater, container, false)

        setData()

        return binding.root
    }

    private fun setData() {
        val id = movieDetailViewModel.movieDetail.value?.id ?: 0
        movieTrailersViewModel.getMovieTrailersById(id)
        movieTrailersViewModel.movieTrailersLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.trailersRecyclerView.adapter = MovieTrailersAdapter(
                    movieTrailersViewModel.movieTrailers.value!!,
                )

                binding.progressBar.visibility = View.GONE
                binding.trailersRecyclerView.visibility = View.VISIBLE
            }
        }
    }
}