package fi.lauriari.recipe_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fi.lauriari.recipe_app.navigation.destinations.detailedRecipeComposable
import fi.lauriari.recipe_app.navigation.destinations.searchComposable
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun InitNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SEARCH_SCREEN
    ) {

        searchComposable(
            mainViewModel = mainViewModel,
            navigateToDetailedRecipeScreen = screen.detailedRecipe
        )

        detailedRecipeComposable(
            mainViewModel = mainViewModel
        )
    }
}