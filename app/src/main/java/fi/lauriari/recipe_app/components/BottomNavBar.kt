package fi.lauriari.recipe_app.components

import android.widget.Toast
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
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun BottomNavBar(
    mainViewModel: MainViewModel,
    navigateToFavoriteScreen: () -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }
    val context = LocalContext.current
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
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
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
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
                navigateToFavoriteScreen()
            })
    }
}