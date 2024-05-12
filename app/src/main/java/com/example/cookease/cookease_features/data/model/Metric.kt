package com.example.cookease.cookease_features.data.model

import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitShort")
    val unitShort: String
)
