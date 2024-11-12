package com.mercan.retrofitmvvm.ui.adapter.homeslider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.MovieCardBigBinding

class HomeSliderAdapter(
    private val movies: MovieList,
    private val genres: GenreList,
) : RecyclerView.Adapter<HomeSliderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        val binding = MovieCardBigBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeSliderViewHolder(binding)
    }

    override fun getItemCount() = movies.results?.size ?: 0

    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        holder.bind(movies.results?.get(position), genres)
    }
}