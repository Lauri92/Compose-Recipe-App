package fi.lauriari.recipe_app.navigation

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fi.lauriari.recipe_app.navigation.destinations.detailedFavoriteComposable
import fi.lauriari.recipe_app.navigation.destinations.detailedRecipeComposable
import fi.lauriari.recipe_app.navigation.destinations.favoritesComposable
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

    val listState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = SEARCH_SCREEN
    ) {

        searchComposable(
            mainViewModel = mainViewModel,
            navigateToDetailedRecipeScreen = screen.detailedRecipe,
            navigateToFavoritesScreen = screen.favorites,
            listState = listState
        )

        detailedRecipeComposable(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = screen.search
        )

        favoritesComposable(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = screen.search,
            navigateToDetailedFavoriteScreen = screen.detailedFavorite
        )

        detailedFavoriteComposable(
            mainViewModel = mainViewModel,
            navigateToFavoritesScreen = screen.favorites
        )
    }
}