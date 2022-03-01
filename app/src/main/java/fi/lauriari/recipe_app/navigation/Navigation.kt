package fi.lauriari.recipe_app.navigation

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import fi.lauriari.recipe_app.R
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
    val favoritesListState = rememberLazyListState()
    var image: Painter? = null
    when ((0..2).random()) {
        0 -> {
            image = painterResource(id = R.drawable.background1)
        }
        1 -> {
            image = painterResource(id = R.drawable.background2)
        }
        2 -> {
            image = painterResource(id = R.drawable.background3)
        }
    }

    NavHost(
        navController = navController,
        startDestination = SEARCH_SCREEN
    ) {

        searchComposable(
            mainViewModel = mainViewModel,
            navigateToDetailedRecipeScreen = screen.detailedRecipe,
            navigateToFavoritesScreen = screen.favorites,
            listState = listState,
            image = image
        )

        detailedRecipeComposable(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = screen.search
        )

        favoritesComposable(
            mainViewModel = mainViewModel,
            favoritesListState = favoritesListState,
            navigateToSearchScreen = screen.search,
            navigateToDetailedFavoriteScreen = screen.detailedFavorite
        )

        detailedFavoriteComposable(
            mainViewModel = mainViewModel,
            navigateToFavoritesScreen = screen.favorites
        )
    }
}