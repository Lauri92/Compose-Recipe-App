package fi.lauriari.recipe_app.repository


import fi.lauriari.recipe_app.data.api.RecipeApiRetrofitInstance
import fi.lauriari.recipe_app.data.model.SearchResult
import retrofit2.Response

class RecipeRepository {

    suspend fun getSomeDataFromApi(
        appIdValue: String,
        appKeyValue: String
    ): Response<SearchResult> {
        return RecipeApiRetrofitInstance.api.getSomeDataFromApi(appIdValue, appKeyValue)
    }

}