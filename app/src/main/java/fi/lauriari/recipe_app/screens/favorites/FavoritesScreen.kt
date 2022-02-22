package fi.lauriari.recipe_app.screens.favorites

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FavoritesScreen(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToDetailedFavoriteScreen: (String) -> Unit
) {

    val allFavoriteRecipes = mainViewModel.allFavoriteRecipes.collectAsState()

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
                allFavoriteRecipes = allFavoriteRecipes,
                navigateToDetailedFavoriteScreen = navigateToDetailedFavoriteScreen
            )
        }
    )
}