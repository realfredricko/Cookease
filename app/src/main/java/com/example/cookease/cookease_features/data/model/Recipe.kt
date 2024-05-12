package com.example.cookease.cookease_features.data.model

import androidx.room.Entity
import com.example.cookease.cookease_features.utils.constants.Constants.RECIPE_TABLE
import com.google.gson.annotations.SerializedName
@Entity(tableName = RECIPE_TABLE)
data class Recipe(
    @SerializedName("aisle")
    val aisle: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("measures")
    val measures: Measures,
    @SerializedName("name")
    val name: String,
    @SerializedName("nameClean")
    val original: String,
    @SerializedName("originalName")
    val originalName: String,
    @SerializedName("unit")
    val unit: String
)