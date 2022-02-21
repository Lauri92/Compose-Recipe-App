package fi.lauriari.recipe_app.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "ingredient_lines_table")
data class IngredientLine(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val recipeId: String,
    val description: String,
)

data class RecipeWithIngredientLines(
    @Embedded val favoriteRecipe: FavoriteRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredientLines: List<IngredientLine>
)