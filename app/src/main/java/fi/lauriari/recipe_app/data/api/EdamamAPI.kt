package fi.lauriari.recipe_app.data.api

import fi.lauriari.recipe_app.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamAPI {

    //@GET("v2?type=public&q=nuggets&app_id=688c6104&app_key=a040227ff9006924bc0209dd102512f5&dishType=Salad")
    //suspend fun getSomeDataFromApi(): Response<SearchResult>

    @GET("v2?type=public&q=nuggets")
    suspend fun getSomeDataFromApi(
        @Query("app_id") appIdValue: String,
        @Query("app_key") appKeyValue: String,
        @Query("dishType") dishType: String = "salad",
    ): Response<SearchResult>


}