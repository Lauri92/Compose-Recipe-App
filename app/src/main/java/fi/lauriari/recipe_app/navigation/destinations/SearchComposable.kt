package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.search.SearchScreen
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.searchComposable(
    navigateToMainMenuScreen: () -> Unit,
    mainViewModel: MainViewModel
) {
    composable(
        route = SEARCH_SCREEN,
    ) { navBackStackEntry ->

        SearchScreen(
            navigateToMainMenuScreen = navigateToMainMenuScreen,
            mainViewModel = mainViewModel
        )
    }
}