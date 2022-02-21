package fi.lauriari.recipe_app.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import fi.lauriari.recipe_app.ui.theme.bottomNavigationGray
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.util.Constants.FAVORITES_SCREEN
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun BottomNavBar(
    navigateToSearchScreen: () -> Unit,
    navigateToFavoritesScreen: () -> Unit,
    mainViewModel: MainViewModel
) {

    var selectedItem by mainViewModel.selectedScreen

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        BottomNavigationItem(
            selectedContentColor = bottomNavigationOrange,
            unselectedContentColor = bottomNavigationGray,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search bottom nav item",
                )
            },
            label = {
                Text(
                    text = "Find",
                )
            },
            selected = selectedItem == SEARCH_SCREEN,
            onClick = {
                selectedItem = SEARCH_SCREEN
                navigateToSearchScreen()
            }
        )
        BottomNavigationItem(
            selectedContentColor = bottomNavigationOrange,
            unselectedContentColor = bottomNavigationGray,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "My Recipes bottom nav item",
                )
            },
            label = {
                Text(
                    text = "My Recipes",
                )
            },
            selected = selectedItem == FAVORITES_SCREEN,
            onClick = {
                selectedItem = FAVORITES_SCREEN
                navigateToFavoritesScreen()
            })
    }
}