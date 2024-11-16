package com.mercan.retrofitmvvm.ui.adapter.detail

import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.credits.Cast
import com.mercan.retrofitmvvm.databinding.PlayerCardItemBinding
import com.squareup.picasso.Picasso

class DetailPlayersViewHolder(private val binding: PlayerCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(player: Cast?) {
        val imagePath = Constants.IMAGE_BASE_URL + player?.profilePath

        Picasso.get().load(imagePath).into(binding.portraitImageView)
        binding.realNameTextView.text = player?.originalName
        binding.characterNameTextView.text = player?.character
    }
}