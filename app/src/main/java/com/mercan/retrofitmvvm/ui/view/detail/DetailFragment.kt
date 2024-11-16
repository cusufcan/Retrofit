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
import com.mercan.retrofitmvvm.data.model.credits.Credits
import com.mercan.retrofitmvvm.data.model.detail.MovieDetail
import com.mercan.retrofitmvvm.databinding.FragmentDetailBinding
import com.mercan.retrofitmvvm.ui.adapter.detail.DetailPlayersAdapter
import com.mercan.retrofitmvvm.ui.view.main.MainActivity
import com.mercan.retrofitmvvm.ui.viewmodel.MovieCreditsViewModel
import com.mercan.retrofitmvvm.ui.viewmodel.MovieDetailViewModel
import com.mercan.retrofitmvvm.utils.formatDate
import com.mercan.retrofitmvvm.utils.formatToString
import com.mercan.retrofitmvvm.utils.formatToTime
import com.squareup.picasso.Picasso
import java.util.Locale

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    private val movieCreditsViewModel: MovieCreditsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        movieDetailViewModel.getMovieById(movieId ?: 0)
        movieDetailViewModel.movieDetailLoading.observe(viewLifecycleOwner) { isLoading ->
            if (!isLoading) {
                movieCreditsViewModel.getMovieCreditsById(movieId ?: 0)
                movieCreditsViewModel.movieCreditsLoading.observe(viewLifecycleOwner) { isCreditLoading ->
                    if (!isCreditLoading) {
                        binding.progressBar.visibility = View.GONE
                        binding.mainScrollView.visibility = View.VISIBLE

                        val movieDetail = movieDetailViewModel.movieDetail.value
                        val movieCredits = movieCreditsViewModel.movieCredits.value
                        bind(movieDetail, movieCredits)
                    }
                }
            }
        }
    }

    private fun bind(
        movieDetail: MovieDetail?,
        movieCredits: Credits?,
    ) {
        val imagePath = Constants.IMAGE_BASE_URL + movieDetail?.posterPath
        val formattedAverage = String.format(
            Locale.getDefault(),
            "%.1f",
            movieDetail?.voteAverage,
        )
        val formattedDate = movieDetail?.releaseDate?.formatDate()
        val directorName = movieCredits?.crew?.find { it.job == "Director" }?.name
        val playersAdapter = DetailPlayersAdapter(movieCredits?.cast)

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
        println(movieCredits)
        binding.directorNameChip.text = directorName

        // Overview
        binding.overviewTextView.text = movieDetail?.overview

        // PlayersAdapter
        binding.playersRecyclerView.adapter = playersAdapter

        // TabLayoutAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}