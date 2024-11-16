package com.mercan.retrofitmvvm.ui.adapter.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mercan.retrofitmvvm.R
import com.mercan.retrofitmvvm.ui.view.detail.recommendations.RecommendationsFragment
import com.mercan.retrofitmvvm.ui.view.detail.trailers.TrailersFragment

class DetailTabLayoutAdapter(
    fm: FragmentManager,
    private val context: Context,
) : FragmentStatePagerAdapter(fm) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RecommendationsFragment()
            1 -> TrailersFragment()
            else -> throw Exception()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.recommendations)
            1 -> context.getString(R.string.trailers)
            else -> throw Exception()
        }
    }
}