package com.mercan.retrofitmvvm.ui.adapter.homeslider

import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.R
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.Movie
import com.mercan.retrofitmvvm.databinding.MovieCardBigBinding
import com.mercan.retrofitmvvm.utils.findGenresByIds
import com.mercan.retrofitmvvm.utils.formatToString
import com.squareup.picasso.Picasso
import java.util.Locale

class HomeSliderViewHolder(private val binding: MovieCardBigBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        movie: Movie?,
        genreList: GenreList,
        onItemClick: (Movie?) -> Unit,
    ) {
        val path = Constants.IMAGE_BASE_URL + movie?.posterPath
        val formattedAverage = String.format(
            Locale.getDefault(),
            "%.1f",
            movie?.voteAverage,
        )
        val genres = genreList.findGenresByIds(movie?.genreIds)

        Picasso.get().load(path).into(binding.imageView)
        binding.titleTextView.text = movie?.title
        binding.reviewTextView.text = binding.root.context.getString(
            R.string.movie_rating,
            formattedAverage,
            movie?.voteCount?.formatToString(),
        )
        binding.genresTextView.text = genres.joinToString(", ") { it.name }

        binding.root.setOnClickListener {
            onItemClick(movie)
        }
    }
}