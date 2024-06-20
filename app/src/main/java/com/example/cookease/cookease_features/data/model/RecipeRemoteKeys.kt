package com.example.cookease.cookease_features.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookease.cookease_features.utils.constants.Constants.RECIPE_REMOTE_KEY_TABLE

@Entity(tableName = RECIPE_REMOTE_KEY_TABLE)
data class RecipeRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevKey:Int?,
    val nextKey:Int?
)
