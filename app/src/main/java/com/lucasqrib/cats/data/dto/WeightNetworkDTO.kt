package com.lucasqrib.cats.data.dto

import com.google.gson.annotations.SerializedName

data class WeightNetworkDTO(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String
)