package fi.lauriari.recipe_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoriteRecipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipe_table")
    suspend fun getAllFavoriteRecipes(): List<FavoriteRecipe>

    @Query("DELETE FROM favorite_recipe_table where id= :id")
    suspend fun deleteFavoriteRecipe(id: String)

    @Query("SELECT * FROM favorite_recipe_table WHERE id= :id")
    fun getRecipeById(id: String): Flow<FavoriteRecipe?>

}