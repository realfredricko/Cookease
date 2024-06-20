package com.example.cookease.cookease_features.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cookease.cookease_features.data.local.CookeaseDatabase
import com.example.cookease.cookease_features.data.model.Recipe
import com.example.cookease.cookease_features.data.paging.RecipeRemoteMediator
import com.example.cookease.cookease_features.data.paging.RecipeSearchPagingSource
import com.example.cookease.cookease_features.data.remote.CookeaseAPI
import kotlinx.coroutines.flow.Flow

/**
 * Handles logic for caching data and synchronizing it with
 * remote source
 * It acts as a single source of truth for data operation
 * by abstracting the data sources*/
class Repository(
    private val cookeaseAPI: CookeaseAPI,
    private val cookeaseDatabase: CookeaseDatabase
) {
    /**
     *Get recipes from the local database*/
    @OptIn(ExperimentalPagingApi::class)
    fun getRecipes():Flow<PagingData<Recipe>>{
        /*val pagingSourceFactory = {
            cookeaseDatabase.recipeDao().getRecipes()
        }*/
      return Pager(
          config = PagingConfig(pageSize = 10),
          remoteMediator = RecipeRemoteMediator(
              cookeaseAPI = cookeaseAPI,
              cookeaseDatabase = cookeaseDatabase
          ),
          pagingSourceFactory = {
              cookeaseDatabase.recipeDao().getRecipes()
          }
      ).flow
          }
    /**
     *Search for recipes with query*/
    fun searchRecipes(): Flow<PagingData<Recipe>> {
return Pager(
    config = PagingConfig(pageSize = 10),
    pagingSourceFactory = {
        RecipeSearchPagingSource(cookeaseAPI = cookeaseAPI, query = "")
    }
).flow
    }
}