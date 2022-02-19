package fi.lauriari.recipe_app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipe_table")
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val label: String,
    val imageUrl: String,
    val instructionsUrl: String
)