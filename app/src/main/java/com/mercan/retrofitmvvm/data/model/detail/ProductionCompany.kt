package com.mercan.retrofitmvvm.data.model.detail

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Long,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String,
)
