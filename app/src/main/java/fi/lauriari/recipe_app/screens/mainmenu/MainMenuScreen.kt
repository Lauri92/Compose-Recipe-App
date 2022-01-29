package fi.lauriari.recipe_app.screens.mainmenu

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun MainMenuScreen(
    navigateToSearchScreen: () -> Unit
) {
    Scaffold(
        content = {
            MainMenuContent(
                navigateToSearchScreen = navigateToSearchScreen
            )
        }
    )
}