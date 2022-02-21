package fi.lauriari.recipe_app.screens.favorites

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FavoritesScreen(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                mainViewModel = mainViewModel,
                navigateToSearchScreen = navigateToSearchScreen,
                navigateToFavoritesScreen = {}
            )
        },
        content = {
            Text("hello")
        }
    )
}