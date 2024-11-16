package com.mercan.retrofitmvvm.ui.adapter.detail.trailers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.data.model.detail.trailers.TrailerList
import com.mercan.retrofitmvvm.databinding.MovieTrailerItemBinding

class MovieTrailersAdapter(
    private val trailers: TrailerList,
) : RecyclerView.Adapter<MovieTrailersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrailersViewHolder {
        val binding = MovieTrailerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieTrailersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieTrailersViewHolder, position: Int) {
        holder.bind(trailers.results[position])
    }

    override fun getItemCount() = trailers.results.size
}