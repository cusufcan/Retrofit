package com.mercan.retrofitmvvm.utils

import com.mercan.retrofitmvvm.data.model.Genre
import com.mercan.retrofitmvvm.data.model.GenreList

fun GenreList.findGenresByIds(genreIds: List<Long>?): List<Genre> {
    return this.genres.filter {
        genreIds?.contains(it.id) ?: false
    }.toList()
}