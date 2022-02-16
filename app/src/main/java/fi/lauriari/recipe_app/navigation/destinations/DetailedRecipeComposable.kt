package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.detailedrecipe.DetailedRecipeScreen
import fi.lauriari.recipe_app.util.Constants
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.detailedRecipeComposable(
    mainViewModel: MainViewModel,
) {
    composable(
        route = Constants.DETAILED_RECIPE_SCREEN,
    ) { navBackStackEntry ->

        DetailedRecipeScreen(
            mainViewModel = mainViewModel,
        )
    }
}