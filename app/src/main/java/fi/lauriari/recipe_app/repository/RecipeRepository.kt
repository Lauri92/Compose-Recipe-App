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

    suspend fun getSomeDataFromApi(
        appIdValue: String,
        appKeyValue: String
    ): Flow<Response<EdamamSearchResult>> {
        return flow {
            val searchResult = api.getSomeDataFromApi(appIdValue, appKeyValue)
            emit(searchResult)
        }.flowOn(context = Dispatchers.IO) // Optional
    }

}