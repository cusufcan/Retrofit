package com.mercan.retrofitmvvm.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mercan.retrofitmvvm.R
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.detail.MovieDetail
import com.mercan.retrofitmvvm.databinding.FragmentDetailBinding
import com.mercan.retrofitmvvm.ui.view.main.MainActivity
import com.mercan.retrofitmvvm.ui.viewmodel.MovieDetailViewModel
import com.mercan.retrofitmvvm.utils.formatDate
import com.mercan.retrofitmvvm.utils.formatToString
import com.mercan.retrofitmvvm.utils.formatToTime
import com.squareup.picasso.Picasso
import java.util.Locale

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        hideBottomBar()
        setOnClickListeners()
        setData()

        return binding.root
    }

    private fun hideBottomBar() {
        val mainActivity = activity as MainActivity
        mainActivity.binding.appBottomNavigationView.visibility = View.GONE
    }

    private fun setOnClickListeners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setData() {
        val movieId = arguments?.getLong("id")
        movieViewModel.getMovieById(movieId ?: 0)
        movieViewModel.movieDetailLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                binding.progressBar.visibility = View.GONE
                binding.mainScrollView.visibility = View.VISIBLE

                val movieDetail = movieViewModel.movieDetail.value
                bind(movieDetail)
            }
        }
    }

    private fun bind(movieDetail: MovieDetail?) {
        val imagePath = Constants.IMAGE_BASE_URL + movieDetail?.posterPath
        val formattedAverage = String.format(
            Locale.getDefault(),
            "%.1f",
            movieDetail?.voteAverage,
        )
        val formattedDate = movieDetail?.releaseDate?.formatDate()

        // Image
        Picasso.get().load(imagePath).into(binding.imageView)

        // Title
        binding.titleTextView.text = movieDetail?.title

        // Rating
        binding.ratingBar.rating = movieDetail?.voteAverage?.toFloat()?.div(2) ?: 0f
        binding.ratingTextView.text = binding.root.context.getString(
            R.string.movie_rating_detail,
            formattedAverage,
            movieDetail?.voteCount?.formatToString(),
        )

        // Genres
        binding.genresTextView.text = movieDetail?.genres?.joinToString { it.name }

        // Year
        binding.yearTextView.text = formattedDate

        // Duration
        binding.durationTextView.text = movieDetail?.runtime?.formatToTime()

        // Director

        // Overview
        binding.overviewTextView.text = movieDetail?.overview

        // PlayersAdapter

        // TabLayoutAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}