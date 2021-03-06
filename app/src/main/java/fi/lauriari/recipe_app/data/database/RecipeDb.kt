package fi.lauriari.recipe_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fi.lauriari.recipe_app.data.dao.FavoriteRecipeDao
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.IngredientLine

@Database(
    entities = [FavoriteRecipe::class, IngredientLine::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteRecipeDatabase : RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
}