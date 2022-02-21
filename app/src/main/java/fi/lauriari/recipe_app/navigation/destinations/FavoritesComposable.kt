package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.favorites.FavoritesScreen
import fi.lauriari.recipe_app.util.Constants.FAVORITES_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.favoritesComposable(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit
) {
    composable(
        route = FAVORITES_SCREEN
    ) { navBackStackEntry ->

        FavoritesScreen(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = navigateToSearchScreen
        )

    }
}