package com.mercan.retrofitmvvm.ui.adapter.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.data.model.credits.Cast
import com.mercan.retrofitmvvm.databinding.PlayerCardItemBinding

class DetailPlayersAdapter(private val cast: List<Cast>?) :
    RecyclerView.Adapter<DetailPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPlayersViewHolder {
        val binding = PlayerCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DetailPlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailPlayersViewHolder, position: Int) {
        val player = cast?.get(position)
        holder.bind(player)
    }

    override fun getItemCount() = cast?.size ?: 0
}