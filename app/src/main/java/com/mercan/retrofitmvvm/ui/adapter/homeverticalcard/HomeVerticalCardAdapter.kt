package com.mercan.retrofitmvvm.ui.adapter.homeverticalcard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.core.Route
import com.mercan.retrofitmvvm.data.model.GenreList
import com.mercan.retrofitmvvm.data.model.MovieList
import com.mercan.retrofitmvvm.databinding.MovieCardVerticalBinding
import com.mercan.retrofitmvvm.ui.bottomsheet.HomeBottomSheetDialog

class HomeVerticalCardAdapter(
    private val fragmentManager: FragmentManager,
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
        holder.bind(movies.results?.get(position), genres) {
            HomeBottomSheetDialog(
                it,
                Route.HOME,
            ).show(fragmentManager, Constants.NORMAL_TAG)
        }
    }

    override fun getItemCount() = movies.results?.size ?: 0
}