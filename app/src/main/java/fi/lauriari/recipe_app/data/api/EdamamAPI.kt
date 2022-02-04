package fi.lauriari.recipe_app.data.api

import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamAPI {

    @GET("v2?type=public&q=nuggets")
    suspend fun getSomeDataFromApi(
        @Query("app_id") appIdValue: String,
        @Query("app_key") appKeyValue: String,
        @Query("dishType") dishType: String = "Sandwiches",
    ): Response<EdamamSearchResult>


}