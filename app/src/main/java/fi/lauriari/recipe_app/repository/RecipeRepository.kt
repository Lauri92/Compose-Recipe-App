package fi.lauriari.recipe_app.repository


import fi.lauriari.recipe_app.data.api.RecipeApiRetrofitInstance
import fi.lauriari.recipe_app.data.api.RecipeApiRetrofitInstance.api
import fi.lauriari.recipe_app.data.model.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RecipeRepository {

    suspend fun getSomeDataFromApi(
        appIdValue: String,
        appKeyValue: String
    ): Flow<Response<SearchResult>> {
        return flow {
            val searchResult = api.getSomeDataFromApi(appIdValue, appKeyValue)
            emit(searchResult)
        }.flowOn(Dispatchers.IO)
    }

}