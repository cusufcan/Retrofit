package com.mercan.retrofitmvvm.ui.adapter.detail.trailers

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import androidx.recyclerview.widget.RecyclerView
import com.mercan.retrofitmvvm.data.model.detail.trailers.Trailer
import com.mercan.retrofitmvvm.databinding.MovieTrailerItemBinding

class MovieTrailersViewHolder(
    private val binding: MovieTrailerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetJavaScriptEnabled")
    fun bind(trailer: Trailer?) {
        val videoUrl =
            "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/${trailer?.key}?si=${trailer?.id}\" " +
                    "title=\"${trailer?.name}\" frameborder=\"0\" allow=\"accelerometer; " +
                    "autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; " +
                    "web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" " +
                    "allowfullscreen></iframe>"
        binding.webView.loadData(videoUrl, "text/html", "utf-8")
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = WebChromeClient()

        binding.webView.setBackgroundColor(0)
        binding.webView.setBackgroundResource(android.R.color.transparent)

        binding.videoTitleTextView.text = trailer?.name
        binding.videoTypeTextView.text = trailer?.type
    }
}