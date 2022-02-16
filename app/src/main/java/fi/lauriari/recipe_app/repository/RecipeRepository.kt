package fi.lauriari.recipe_app.repository


import android.util.Log
import fi.lauriari.recipe_app.data.api.RecipeApiRetrofitInstance.api
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RecipeRepository {

    suspend fun getRecipesByQuery(
        appIdValue: String,
        appKeyValue: String,
        searchQuery: String,
        cuisineType: String?,
        mealType: String?,
        dishType: String?
    ): Flow<Response<EdamamSearchResult>> {
        return flow {
            val searchResult = api.getRecipesByQuery(
                appIdValue = appIdValue,
                appKeyValue = appKeyValue,
                searchQuery = searchQuery,
                cuisineType = cuisineType,
                mealType = mealType,
                dishType = dishType
            )
            emit(searchResult)
        }.flowOn(context = Dispatchers.IO) // Optional
    }


    suspend fun getMoreRecipes(
        nextPageUrl: String,
    ): Flow<Response<EdamamSearchResult>> {
        return flow {
            val searchResult =
                api.getMoreRecipes(nextPageUrl = nextPageUrl)
            emit(searchResult)
        }.flowOn(context = Dispatchers.IO) // Optional
    }
}