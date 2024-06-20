package com.example.cookease.cookease_features.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Query
import com.example.cookease.cookease_features.data.model.Recipe
import com.example.cookease.cookease_features.data.remote.CookeaseAPI
import com.example.cookease.cookease_features.utils.constants.Constants.STARTING_PAGE_INDEX

/**
 * Responsible for loading paginated data
 * directly from the remote data source[CookeaseAPI] based
 * on search query. It does not interact with
 * the local cache*/
class RecipeSearchPagingSource(
    private val cookeaseAPI: CookeaseAPI,
    private val query:String
): PagingSource<Int, Recipe>() {
    /**provides  a key used for the initial load for the pagingSource due
    to the invalidation of the existing pagingSource.The key
    is provided to load via LoadParams.key.The last accessed position can be retrieved via
    state.anchorPosition
    */
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let {
            anchorPosition ->
state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
    ?:
    state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
       val page = params.key ?:1
        return try {
            val response = cookeaseAPI.searchRecipes(query, perPage = 10)
            val endOfPaginationReached = response.results.isEmpty()
            if(response.results.isNotEmpty()){
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (page==1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1
                )
            }
            else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}