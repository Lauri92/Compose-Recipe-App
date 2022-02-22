package fi.lauriari.recipe_app.repository


import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import fi.lauriari.recipe_app.data.api.RecipeApiRetrofitInstance.api
import fi.lauriari.recipe_app.data.dao.FavoriteRecipeDao
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.IngredientLine
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class RecipeRepository @Inject constructor(
    private val favoriteRecipeDao: FavoriteRecipeDao
) {

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

    val getAllFavoriteRecipes: Flow<List<RecipeWithIngredientLines>> =
        favoriteRecipeDao.getAllFavoriteRecipes()

    suspend fun insertFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        return favoriteRecipeDao.insertFavoriteRecipe(favoriteRecipe)
    }

    suspend fun deleteFavoriteRecipe(id: String) {
        favoriteRecipeDao.deleteFavoriteRecipe(id)
    }

    suspend fun insertIngredientLine(ingredientLine: IngredientLine) {
        favoriteRecipeDao.insertIngredientLine(ingredientLine)
    }

    suspend fun deleteIngredientLines(recipeId: String) {
        favoriteRecipeDao.deleteIngredientLines(recipeId)
    }


    fun getRecipeById(id: String): Flow<FavoriteRecipe?> {
        return favoriteRecipeDao.getRecipeById(id)
    }

    fun getSelectedFavorite(favoriteId: String): Flow<RecipeWithIngredientLines> {
        return favoriteRecipeDao.getSelectedFavorite(favoriteId)
    }

}