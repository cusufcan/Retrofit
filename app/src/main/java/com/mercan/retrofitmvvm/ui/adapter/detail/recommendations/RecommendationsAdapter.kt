package com.mercan.retrofitmvvm.ui.adapter.detail.recommendations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.core.Route
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.MovieCardBigWrapBinding
import com.mercan.retrofitmvvm.ui.bottomsheet.HomeBottomSheetDialog

class RecommendationsAdapter(
    private val fragmentManager: FragmentManager,
    private val movieList: MovieList,
    private val genreList: GenreList,
) :
    RecyclerView.Adapter<RecommendationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsViewHolder {
        val binding = MovieCardBigWrapBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecommendationsViewHolder(binding)
    }

    override fun getItemCount() = movieList.results?.size ?: 0

    override fun onBindViewHolder(holder: RecommendationsViewHolder, position: Int) {
        holder.bind(
            movieList.results?.get(position),
            genreList,
        ) {
            HomeBottomSheetDialog(
                it,
                Route.DETAILS,
            ).show(fragmentManager, Constants.NORMAL_TAG)
        }
    }

}