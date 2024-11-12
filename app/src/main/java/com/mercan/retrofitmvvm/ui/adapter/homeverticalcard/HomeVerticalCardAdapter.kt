package com.mercan.retrofitmvvm.ui.adapter.homeverticalcard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.MovieCardVerticalBinding

class HomeVerticalCardAdapter(
    private val movies: MovieList,
    private val genres: GenreList,
) : RecyclerView.Adapter<HomeVerticalCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVerticalCardViewHolder {
        val binding = MovieCardVerticalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeVerticalCardViewHolder(binding, binding.root.context)
    }

    override fun onBindViewHolder(holder: HomeVerticalCardViewHolder, position: Int) {
        holder.bind(movies.results?.get(position), genres)
    }

    override fun getItemCount() = movies.results?.size ?: 0
}