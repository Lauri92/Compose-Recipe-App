package fi.lauriari.recipe_app.screens.detailedfavorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.screens.detailedrecipe.SingleRecipeTopBar
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.viewmodels.MainViewModel


@Composable
fun DetailedFavoriteScreen(
    selectedFavorite: RecipeWithIngredientLines?,
    navigateToFavoritesScreen: () -> Unit,
    isRecipeFavorited: Boolean,
    mainViewModel: MainViewModel
) {

    val ingredientLines = selectedFavorite?.ingredientLines?.map {
        it.description
    }

    Scaffold(
        topBar = {
            SingleRecipeTopBar(
                isRecipeFavorited = isRecipeFavorited,
                navigateToPreviousScreen = {
                    navigateToFavoritesScreen()
                },
                onInsertFavorite = {
                    if (ingredientLines != null) {
                        mainViewModel.insertFavoriteRecipe(
                            activeRecipe = selectedFavorite.favoriteRecipe,
                            ingredientLines = ingredientLines
                        )
                    }
                },
                onRemoveFavorite = {
                    mainViewModel.deleteFavoriteRecipe(selectedFavorite?.favoriteRecipe!!.id)
                }
            )
        },
        content = {
            DetailedFavoriteContent(
                selectedFavorite = selectedFavorite
            )
        })

}