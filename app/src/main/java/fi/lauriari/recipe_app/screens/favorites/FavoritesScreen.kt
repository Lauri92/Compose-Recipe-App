package fi.lauriari.recipe_app.screens.favorites

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FavoritesScreen(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToDetailedFavoriteScreen: (String) -> Unit,
    favoritesListState: LazyListState
) {

    val allFavoriteRecipes = mainViewModel.selectedFavoriteRecipes.collectAsState()
    val searchFavoriteRecipesTextState: String by mainViewModel.searchFavoriteRecipesTextState

    Scaffold(
        bottomBar = {
            BottomNavBar(
                mainViewModel = mainViewModel,
                navigateToSearchScreen = navigateToSearchScreen,
                navigateToFavoritesScreen = {}
            )
        },
        content = {
            FavoritesContent(
                mainViewModel = mainViewModel,
                searchFavoriteRecipesTextState = searchFavoriteRecipesTextState,
                favoritesListState = favoritesListState,
                allFavoriteRecipes = allFavoriteRecipes,
                navigateToDetailedFavoriteScreen = navigateToDetailedFavoriteScreen
            )
        }
    )
}