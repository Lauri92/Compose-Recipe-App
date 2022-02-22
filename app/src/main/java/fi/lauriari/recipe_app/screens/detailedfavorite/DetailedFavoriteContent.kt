package fi.lauriari.recipe_app.screens.detailedfavorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange

@Composable
fun DetailedFavoriteContent(
    selectedFavorite: RecipeWithIngredientLines?,
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth,
            painter = rememberImagePainter(
                data = selectedFavorite?.favoriteRecipe?.imageUrl,
                builder = {
                    //placeholder()
                    //error()
                    crossfade(500)
                }
            ),
            contentDescription = "Image of a recipe",
        )
        Text(
            modifier = Modifier.padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            ),
            text = selectedFavorite?.favoriteRecipe?.label ?: "Couldn't fetch name of the recipe!",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            ),
            text = "Ingredients",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            selectedFavorite?.ingredientLines?.forEach { currentIngredient ->
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = currentIngredient.description
                    )
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(1.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 10.dp, end = 10.dp, top = 10.dp
                    ),
                    text = "Instructions",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(
                    modifier = Modifier
                        .padding(bottom = 50.dp),
                    onClick = {
                        if (selectedFavorite?.favoriteRecipe?.instructionsUrl != null) {
                            uriHandler.openUri(selectedFavorite.favoriteRecipe.instructionsUrl)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "",
                        tint = bottomNavigationOrange
                    )
                }


            }

        }
    }
}