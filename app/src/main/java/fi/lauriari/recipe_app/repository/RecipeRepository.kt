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

    suspend fun getDataByQuery(
        appIdValue: String,
        appKeyValue: String,
        searchQuery: String
    ): Flow<Response<EdamamSearchResult>> {
        return flow {
            val searchResult = api.getDataByQuery(appIdValue, appKeyValue, searchQuery)
            emit(searchResult)
        }.flowOn(context = Dispatchers.IO) // Optional
    }

    suspend fun getMoreRecipesFromApi(
        appIdValue: String,
        appKeyValue: String,
        searchQuery: String,
        nextpageContQuery: String,
    ): Flow<Response<EdamamSearchResult>> {
        Log.d("nextpagedatarepotest", "$appIdValue, $appKeyValue, $searchQuery, $nextpageContQuery")
        return flow {
            val searchResult =
                api.getMoreRecipes(
                    appIdValue = appIdValue,
                    appKeyValue = appKeyValue,
                    searchQuery = searchQuery,
                    nextpageContQuery = nextpageContQuery
                )
            emit(searchResult)
        }.flowOn(context = Dispatchers.IO) // Optional
    }


}