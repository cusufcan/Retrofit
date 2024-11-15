package com.mercan.retrofitmvvm.utils

import android.icu.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun Number.formatToString(): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = this.toDouble()
    val value = floor(log10(numValue)).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(numValue / 10.0.pow((base * 3.0))) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}

fun Number.formatToTime(): String {
    val hours = this.toInt() / 60
    val minutes = this.toInt() % 60
    return "${hours}h ${minutes}m"
}