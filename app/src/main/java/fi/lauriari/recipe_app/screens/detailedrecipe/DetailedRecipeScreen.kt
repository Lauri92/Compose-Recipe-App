package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel
) {

    mainViewModel.getFavoritedRecipeStatus()
    val isRecipeFavorited by mainViewModel.isRecipeFavorited.collectAsState()

    Scaffold(
        topBar = {
            SingleRecipeTopBar(
                mainViewModel = mainViewModel,
                isRecipeFavorited = isRecipeFavorited
            )
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


