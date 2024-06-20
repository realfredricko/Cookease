package com.example.cookease.cookease_features.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.flow.Flow
import androidx.room.withTransaction
import com.example.cookease.cookease_features.data.local.CookeaseDatabase
import com.example.cookease.cookease_features.data.model.Recipe
import com.example.cookease.cookease_features.data.model.RecipeRemoteKeys
import com.example.cookease.cookease_features.data.remote.CookeaseAPI
import com.example.cookease.cookease_features.utils.constants.Constants.STARTING_PAGE_INDEX

/*
 * Responsible for mediating between the local cache
 * and a remote data source[CookeaseAPI] when fetching
 * paginated data*/
@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val cookeaseDatabase:CookeaseDatabase,
    private val cookeaseAPI: CookeaseAPI
):RemoteMediator<Int,Recipe> () {

    /**
     * Load method fetches new data from the [CookeaseAPI] and saves it to the local database
     */

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Recipe>
    ): MediatorResult {
        val page = when (
            loadType
        ) {
            /**Used to refresh or initial load of a pagingData*/
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            /**Used to load at the start of a pagingData*/
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForInitialItem(state)
                val prevKey = remoteKeys?.prevKey ?://return
                MediatorResult.Success(
                    endOfPaginationReached = true
                )
                prevKey
            }
            /**Used to load at the end of a pagingData*/
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextKey
            }
        }
        try {
            val response = cookeaseAPI.getRecipes(page = page, perPage = 10)
            val endOfPaginationReached = response.isEmpty()
            val prevKey = if (page == STARTING_PAGE_INDEX)
                null else page - 1
            val nextKey = if (endOfPaginationReached)
                null else page + 1

            cookeaseDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    recipeDao.clearRecipes()
                    recipeRemoteKeyDao.clearRemoteKeys()
                }

                val keys = response.map {
                    RecipeRemoteKeys(
                        id = it.id.toString(),
                        prevKey = prevKey,
                        nextKey = nextKey
                    )

                    recipeDao.addRecipe(response)
                    recipeRemoteKeysDao.insertRemoteKeys(keys)
                }
                MediatorResult.Success(
                    endOfPaginationReached =
                    endOfPaginationReached
                )}}
        catch (e: Exception) {
                 return MediatorResult.Error(e)
            }
    }
       private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Recipe>): RecipeRemoteKeys? {
            return state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { id ->
                    recipeRemoteKeyDao.getRemoteKeys(id)
                }
            }

        }

        private suspend fun getRemoteKeyForInitialItem(state: PagingState<Int, Recipe>): RecipeRemoteKeys? {
            return state.pages.firstOrNull() {
                it.data.isNotEmpty()
            }?.data?.firstOrNull()?.let { recipe ->
                recipeRemoteKeyDao.getRemoteKey(recipe.id)
            }
        }

       private  suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Recipe>): RecipeRemoteKeys? {
            return state.pages.lastOrNull() {
                it.data.isNotEmpty()
            }?.data?.lastOrNull()?.let { recipe ->
                recipeRemoteKeyDao.getRemoteKey(recipe.id)
            }
        }
}