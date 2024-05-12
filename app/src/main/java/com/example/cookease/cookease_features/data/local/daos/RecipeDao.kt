package com.example.cookease.cookease_features.data.local.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookease.cookease_features.data.model.Recipe

@Dao
interface RecipeDao {
    //Provides methods for the app to query,insert....
        @Query("SELECT * FROM recipe_table")
        fun getAllRecipe(): PagingSource<Int, Recipe>
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addRecipe(recipe: List<Recipe>)
        @Query("DELETE FROM recipe_table")
        suspend fun clearAllRecipe()
}