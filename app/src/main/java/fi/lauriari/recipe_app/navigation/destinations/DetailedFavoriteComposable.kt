package fi.lauriari.recipe_app.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fi.lauriari.recipe_app.screens.detailedfavorite.DetailedFavoriteScreen
import fi.lauriari.recipe_app.util.Constants.DETAILED_FAVORITE_KEY
import fi.lauriari.recipe_app.util.Constants.DETAILED_FAVORITE_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.detailedFavoriteComposable(
    mainViewModel: MainViewModel,
    navigateToFavoritesScreen: () -> Unit
) {
    composable(
        route = DETAILED_FAVORITE_SCREEN,
        arguments = listOf(navArgument(DETAILED_FAVORITE_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        val selectedFavoriteId = navBackStackEntry.arguments!!.getString(DETAILED_FAVORITE_KEY)

        LaunchedEffect(key1 = selectedFavoriteId) {
            mainViewModel.getSelectedFavorite(selectedFavoriteId.toString())
            mainViewModel.getFavoritedRecipeStatus(selectedFavoriteId.toString())
        }

        val isRecipeFavorited by mainViewModel.isRecipeFavorited.collectAsState()
        val selectedFavorite by mainViewModel.selectedFavorite.collectAsState()

        DetailedFavoriteScreen(
            mainViewModel = mainViewModel,
            selectedFavorite = selectedFavorite,
            isRecipeFavorited = isRecipeFavorited,
            navigateToFavoritesScreen = navigateToFavoritesScreen
        )

    }
}