package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.util.Constants.testItem
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(mainViewModel = mainViewModel)
        },
        content = {
            DetailedRecipeContent(
                mainViewModel = mainViewModel
            )
        }
    )
}


