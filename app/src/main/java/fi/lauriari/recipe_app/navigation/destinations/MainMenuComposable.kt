package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.mainmenu.MainMenuScreen
import fi.lauriari.recipe_app.util.Constants.MAINMENU_SCREEN


fun NavGraphBuilder.mainMenuComposable(
    navigateToSearchScreen: () -> Unit,
) {
    composable(
        route = MAINMENU_SCREEN
    ) {
        MainMenuScreen(
            navigateToSearchScreen = navigateToSearchScreen
        )
    }
}