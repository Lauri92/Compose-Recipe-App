package fi.lauriari.recipe_app.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FavoritesContent(
    mainViewModel: MainViewModel,
    allFavoriteRecipes: State<List<FavoriteRecipe>>
) {

    Column() {
        allFavoriteRecipes.value.forEach {
            Text(it.label)
        }
    }
}