package fi.lauriari.recipe_app.screens.favorites

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.screens.search.SingleRecipe
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesContent(
    mainViewModel: MainViewModel,
    allFavoriteRecipes: State<List<RecipeWithIngredientLines>>,
    navigateToDetailedFavoriteScreen: (String) -> Unit
) {

    val context = LocalContext.current

    val listState = rememberLazyListState()

    LazyVerticalGrid(
        state = listState,
        cells = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(
            start = 21.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
    ) {
        items(
            items = allFavoriteRecipes.value,
        ) { favorite ->
            Column(
                modifier = Modifier
                    .clickable {
                        navigateToDetailedFavoriteScreen(favorite.favoriteRecipe.id)
                        mainViewModel.selectedRecipeWithIngredientLines = favorite
                    }
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = favorite.favoriteRecipe.imageUrl,
                        builder = {
                            //placeholder()
                            //error()
                            crossfade(500)
                            transformations(
                                CircleCropTransformation()
                            )
                        }
                    ),
                    contentDescription = "Image of a recipe",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(all = 2.dp),
                    text = favorite.favoriteRecipe.label
                )
            }
        }
    }
}