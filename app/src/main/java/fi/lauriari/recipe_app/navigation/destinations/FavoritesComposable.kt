package fi.lauriari.recipe_app.navigation.destinations

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.favorites.FavoritesScreen
import fi.lauriari.recipe_app.util.Constants.FAVORITES_SCREEN
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.favoritesComposable(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToDetailedFavoriteScreen: (String) -> Unit,
    favoritesListState: LazyListState
) {
    composable(
        route = FAVORITES_SCREEN
    ) { navBackStackEntry ->

        BackHandler {
            navigateToSearchScreen()
            mainViewModel.selectedScreen.value = SEARCH_SCREEN
        }

        FavoritesScreen(
            mainViewModel = mainViewModel,
            favoritesListState = favoritesListState,
            navigateToSearchScreen = navigateToSearchScreen,
            navigateToDetailedFavoriteScreen = navigateToDetailedFavoriteScreen
        )

    }
}