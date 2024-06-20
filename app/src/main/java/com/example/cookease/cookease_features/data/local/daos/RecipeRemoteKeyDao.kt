package com.example.cookease.cookease_features.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookease.cookease_features.data.model.RecipeRemoteKeys

/** RecipeRemoteKey helps support implementation
 that support remote data sources*/
@Dao
interface RecipeRemoteKeyDao {
    @Query("SELECT * FROM recipe_remote_key_table WHERE id=:id")
    fun getRemoteKeys(id:String): RecipeRemoteKeys
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<RecipeRemoteKeys>)
    @Query("DELETE FROM recipe_remote_key_table")
    suspend fun clearRemoteKeys()
}
/**Provides methods for the app to query,insert....
keys that are used by [RecipeRemoteMediator] to tell the backend service
which data to load next*/


