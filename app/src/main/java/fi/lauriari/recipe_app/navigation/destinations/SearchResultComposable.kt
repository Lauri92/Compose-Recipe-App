package fi.lauriari.recipe_app.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.searchresult.SearchResultScreen
import fi.lauriari.recipe_app.util.Constants.SEARCH_RESULT_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.searchResultComposable(mainViewModel: MainViewModel) {
    composable(
        route = SEARCH_RESULT_SCREEN
    ) { navBackStackEntry ->
        SearchResultScreen(
            mainViewModel = mainViewModel
        )
    }
}