package fi.lauriari.recipe_app.navigation.destinations

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.detailedrecipe.DetailedRecipeScreen
import fi.lauriari.recipe_app.util.Constants
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.detailedRecipeComposable(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
) {
    composable(
        route = Constants.DETAILED_RECIPE_SCREEN,
    ) { navBackStackEntry ->

        mainViewModel.getFavoritedRecipeStatus()
        val isRecipeFavorited by mainViewModel.isRecipeFavorited.collectAsState()

        DetailedRecipeScreen(
            mainViewModel = mainViewModel,
            isRecipeFavorited = isRecipeFavorited,
            navigateToSearchScreen = navigateToSearchScreen
        )
    }
}