package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.viewmodels.MainViewModel


@Composable
fun SingleRecipeTopBar(
    mainViewModel: MainViewModel,
    isRecipeFavorited: Boolean,
    navigateToSearchScreen: () -> Unit
) {

    TopAppBar(
        navigationIcon = {
            BackButton(navigateToSearchScreen = navigateToSearchScreen)
        },
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

@Composable
fun BackButton(
    navigateToSearchScreen: () -> Unit
) {
    IconButton(
        onClick = {
            navigateToSearchScreen()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            tint = bottomNavigationOrange
        )
    }
}