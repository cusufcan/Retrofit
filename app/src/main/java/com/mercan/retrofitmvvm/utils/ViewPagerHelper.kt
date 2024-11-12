package com.mercan.retrofitmvvm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.abs

fun ViewPager2.setCompositeScroll() {
    val compositePageTransformer = CompositePageTransformer()
    compositePageTransformer.addTransformer(MarginPageTransformer(30))
    compositePageTransformer.addTransformer { page, position ->
        val r = 1 - abs(position)
        page.scaleY = 0.80f + r * 0.20f
    }
    this.setPageTransformer(compositePageTransformer)
}

fun ViewPager2.startAutoScroll(lifecycleOwner: LifecycleOwner, interval: Long) {
    var autoScrollJob: Job? = null

    fun startScrolling() {
        if (autoScrollJob?.isActive == true) return
        autoScrollJob = lifecycleOwner.lifecycleScope.launch {
            while (isActive) {
                delay(interval)
                val nextPage = (currentItem + 1) % (adapter?.itemCount ?: 1)
                setCurrentItem(nextPage, true)
            }
        }
    }

    fun stopScrolling() {
        autoScrollJob?.cancel()
    }

    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                stopScrolling()
            } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                startScrolling()
            }
        }
    })

    startScrolling()
}