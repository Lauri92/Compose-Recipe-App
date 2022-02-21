package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.viewmodels.MainViewModel


@Composable
fun SingleRecipeTopBar(
    mainViewModel: MainViewModel,
    isRecipeFavorited: Boolean
) {

    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(text = "")
        },
        actions = {
            if (isRecipeFavorited) {
                RemoveFavoriteRecipeIcon(
                    mainViewModel = mainViewModel
                )
            } else {
                AddFavoriteRecipeIcon(
                    mainViewModel = mainViewModel
                )
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun AddFavoriteRecipeIcon(mainViewModel: MainViewModel) {
    IconButton(
        onClick = {
            mainViewModel.insertFavoriteRecipe()
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "",
        )
    }
}

@Composable
fun RemoveFavoriteRecipeIcon(
    mainViewModel: MainViewModel
) {
    IconButton(
        onClick = {
            mainViewModel.deleteFavoriteRecipe()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "",
            tint = bottomNavigationOrange
        )
    }
}