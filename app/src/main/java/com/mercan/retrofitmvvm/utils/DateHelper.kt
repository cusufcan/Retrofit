package com.mercan.retrofitmvvm.utils

fun String.formatDate(): String {
    val date = this.split("-")
    val year = date[0]
    val month = date[1]
    val day = date[2]

    return "$day.$month.$year"
}