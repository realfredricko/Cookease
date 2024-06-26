package com.example.cookease.cookease_features.data.remote

import com.example.cookease.cookease_features.data.model.Recipe
import com.example.cookease.cookease_features.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CookeaseAPI {


@Headers("")
    //Endpoint for random recipes
    @GET("recipes/random")
    suspend fun getRecipes(
        @Query("page:Int") page: Any,
        @Query("per_page:Int") perPage:Int = 10,
    ):List<Recipe>
    @Headers("")
    @GET("/search")
    suspend fun searchRecipes(
        @Query("query:String")query:String,
        @Query("per_page:Int") perPage:Int = 10,
    ): SearchResponse
}