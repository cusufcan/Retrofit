package com.mercan.retrofitmvvm.ui.adapter.detail

import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.databinding.PlayerCardItemBinding
import com.squareup.picasso.Picasso

class DetailPlayersViewHolder(private val binding: PlayerCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        imagePath: String,
        name: String,
        character: String,
    ) {
        Picasso.get().load(imagePath).into(binding.portraitImageView)
        binding.realNameTextView.text = name
        binding.realNameTextView.text = character
    }
}