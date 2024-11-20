package com.mercan.retrofitmvvm.ui.adapter.seeall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.core.Route
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.Movie
import com.mercan.retrofitmvvm.databinding.MovieCardBigWrapBinding
import com.mercan.retrofitmvvm.ui.bottomsheet.HomeBottomSheetDialog

class SeeAllAdapter(
    private val fragmentManager: FragmentManager,
    private val genres: GenreList? = null,
) : PagingDataAdapter<Movie, SeeAllViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        val binding = MovieCardBigWrapBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SeeAllViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.bind(getItem(position), genres) {
            HomeBottomSheetDialog(
                it,
                Route.SEE_ALL,
            ).show(fragmentManager, Constants.NORMAL_TAG)
        }
        holder.setIsRecyclable(false)
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