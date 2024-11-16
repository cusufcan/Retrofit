package com.mercan.retrofitmvvm.ui.view.detail.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mercan.retrofitmvvm.databinding.FragmentRecommendationsBinding
import com.mercan.retrofitmvvm.ui.adapter.detail.recommendations.RecommendationsAdapter
import com.mercan.retrofitmvvm.ui.viewmodel.MovieDetailViewModel
import com.mercan.retrofitmvvm.ui.viewmodel.MovieRecommendationsViewModel
import com.mercan.retrofitmvvm.ui.viewmodel.MovieViewModel

class RecommendationsFragment : Fragment() {
    private var _binding: FragmentRecommendationsBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    private val movieRecommendationsViewModel: MovieRecommendationsViewModel by activityViewModels()
    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendationsBinding.inflate(inflater, container, false)

        setData()

        return binding.root
    }

    private fun setData() {
        val id = movieDetailViewModel.movieDetail.value?.id ?: 0

        movieRecommendationsViewModel.getRecommendationsById(id)
        movieRecommendationsViewModel.movieRecommendationsLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                val adapter = RecommendationsAdapter(
                    parentFragmentManager,
                    movieRecommendationsViewModel.movieRecommendations.value!!,
                    movieViewModel.genres.value!!
                )

                binding.recommendationsRecyclerView.adapter = adapter

                binding.progressBar.visibility = View.GONE
                binding.recommendationsRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}