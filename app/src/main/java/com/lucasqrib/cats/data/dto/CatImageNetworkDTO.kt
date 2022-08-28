package com.lucasqrib.cats.data.dto

import com.google.gson.annotations.SerializedName

data class CatImageNetworkDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
)
