package fi.lauriari.recipe_app.navigation.destinations

import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.search.SearchScreen
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.searchComposable(
    mainViewModel: MainViewModel,
    navigateToDetailedRecipeScreen: () -> Unit,
    listState: LazyListState,
    navigateToFavoriteScreen: () -> Unit,
) {
    composable(
        route = SEARCH_SCREEN,
    ) { navBackStackEntry ->

        SearchScreen(
            mainViewModel = mainViewModel,
            navigateToDetailedRecipeScreen = navigateToDetailedRecipeScreen,
            navigateToFavoriteScreen = navigateToFavoriteScreen,
            listState = listState
        )
    }
}