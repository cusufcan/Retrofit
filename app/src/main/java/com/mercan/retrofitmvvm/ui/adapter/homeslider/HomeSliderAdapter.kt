package com.mercan.retrofitmvvm.ui.adapter.homeslider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.core.Route
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.Movie
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.MovieCardBigBinding
import com.mercan.retrofitmvvm.ui.bottomsheet.HomeBottomSheetDialog

class HomeSliderAdapter(
    private val fragmentManager: FragmentManager,
    private val movies: MovieList,
    private val genres: GenreList,
) : ListAdapter<Movie, HomeSliderViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        val binding = MovieCardBigBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeSliderViewHolder(binding)
    }

    override fun getItemCount() = movies.results?.size ?: 0

    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        holder.bind(movies.results?.get(position), genres) {
            HomeBottomSheetDialog(
                it,
                Route.HOME,
            ).show(fragmentManager, Constants.NORMAL_TAG)
        }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }
}