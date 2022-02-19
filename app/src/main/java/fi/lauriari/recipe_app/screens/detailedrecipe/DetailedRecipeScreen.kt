package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            SingleRecipeTopBar()
        },
        content = {
            DetailedRecipeContent(
                mainViewModel = mainViewModel
            )
        },
        bottomBar = {
            BottomNavBar(mainViewModel = mainViewModel)
        },
    )
}


