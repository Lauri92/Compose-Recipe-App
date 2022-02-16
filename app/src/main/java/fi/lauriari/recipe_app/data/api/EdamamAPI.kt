package fi.lauriari.recipe_app.data.api

import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface EdamamAPI {

    @GET("v2")
    suspend fun getRecipesByQuery(
        @Query("app_id") appIdValue: String,
        @Query("app_key") appKeyValue: String,
        @Query("q") searchQuery: String,
        @Query("type") type: String = "public",
        @Query("cuisineType") cuisineType: String? = null,
        @Query("mealType") mealType: String? = null,
        @Query("dishType") dishType: String? = null,
    ): Response<EdamamSearchResult>


    @GET
    suspend fun getMoreRecipes(@Url nextPageUrl: String): Response<EdamamSearchResult>


}