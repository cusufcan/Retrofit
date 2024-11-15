package com.mercan.retrofitmvvm.ui.adapter.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.databinding.PlayerCardItemBinding

class DetailPlayersAdapter(private val players: List<String>) :
    RecyclerView.Adapter<DetailPlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPlayersViewHolder {
        val binding = PlayerCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DetailPlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailPlayersViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player, player, player)
    }

    override fun getItemCount() = players.size
}