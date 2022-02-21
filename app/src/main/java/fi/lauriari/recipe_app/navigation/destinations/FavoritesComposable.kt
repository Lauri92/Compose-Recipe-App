package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.FavoritesScreen
import fi.lauriari.recipe_app.util.Constants.FAVORITES_SCREEN

fun NavGraphBuilder.favoritesComposable() {
    composable(
        route = FAVORITES_SCREEN
    ) { navBackStackEntry ->

        FavoritesScreen()

    }
}