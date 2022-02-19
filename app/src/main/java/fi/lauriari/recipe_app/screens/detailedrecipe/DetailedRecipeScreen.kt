package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel
) {
    if (mainViewModel.selectedRecipe != null) {
        Text(text = mainViewModel.selectedRecipe!!.label)
    }
}