package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel,
    isRecipeFavorited: Boolean,
    navigateToSearchScreen: () -> Unit
) {

    Scaffold(
        topBar = {
            SingleRecipeTopBar(
                mainViewModel = mainViewModel,
                isRecipeFavorited = isRecipeFavorited,
                navigateToSearchScreen = navigateToSearchScreen
            )
        },
        content = {
            DetailedRecipeContent(
                mainViewModel = mainViewModel
            )
        },
    )
}


