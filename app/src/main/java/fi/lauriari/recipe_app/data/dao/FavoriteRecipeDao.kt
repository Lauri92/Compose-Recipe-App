package fi.lauriari.recipe_app.data.dao

import androidx.room.*
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.IngredientLine
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

    @Transaction
    @Query("SELECT * FROM favorite_recipe_table")
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithIngredientLines>>

    @Query("DELETE FROM favorite_recipe_table where id= :id")
    suspend fun deleteFavoriteRecipe(id: String)

    @Query("SELECT * FROM favorite_recipe_table WHERE id= :id")
    fun getRecipeById(id: String): Flow<FavoriteRecipe?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredientLine(ingredientLine: IngredientLine)

    @Query("DELETE FROM ingredient_lines_table WHERE recipeId= :recipeId")
    suspend fun deleteIngredientLines(recipeId: String)

    @Query("SELECT * FROM favorite_recipe_table WHERE id= :favoriteId")
    fun getSelectedFavorite(favoriteId: String): Flow<RecipeWithIngredientLines>

}