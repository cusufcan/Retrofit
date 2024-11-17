package com.mercan.retrofitmvvm.data.model.credits

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("gender") val gender: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("department") val department: String,
    @SerializedName("job") val job: String,
)