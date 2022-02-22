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
    isRecipeFavorited: Boolean,
    navigateToSearchScreen: () -> Unit,
    onInsertFavorite: () -> Unit,
    onRemoveFavorite: () -> Unit
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
                    onRemoveFavorite = onRemoveFavorite
                )
            } else {
                AddFavoriteRecipeIcon(
                    onInsertFavorite = onInsertFavorite
                )
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
fun AddFavoriteRecipeIcon(
    onInsertFavorite: () -> Unit
) {
    IconButton(
        onClick = {
            onInsertFavorite()
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
    onRemoveFavorite: () -> Unit
) {
    IconButton(
        onClick = {
            onRemoveFavorite()
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