package fi.lauriari.recipe_app.screens.detailedrecipe

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
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeContent(
    mainViewModel: MainViewModel
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
                data = mainViewModel.selectedRecipe?.image,
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
            text = mainViewModel.selectedRecipe?.label ?: "Couldn't fetch name of the recipe!",
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
            mainViewModel.selectedRecipe?.ingredientLines?.forEach { currentIngredient ->
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = currentIngredient
                    )

                    if (mainViewModel.selectedRecipe?.ingredientLines?.last() != currentIngredient) {
                        Divider(
                            color = Color.LightGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                        )
                    } else {
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
                                    if (mainViewModel.selectedRecipe != null) {
                                        uriHandler.openUri(mainViewModel.selectedRecipe!!.url)
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
        }
    }
}

