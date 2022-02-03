package fi.lauriari.recipe_app.data.api

import fi.lauriari.recipe_app.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeApiRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: EdamamAPI by lazy {
        retrofit.create(EdamamAPI::class.java)
    }

}