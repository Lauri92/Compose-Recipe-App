package fi.lauriari.recipe_app.screens.detailedrecipe

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange
import fi.lauriari.recipe_app.viewmodels.MainViewModel


@Composable
fun SingleRecipeTopBar(mainViewModel: MainViewModel) {

    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(text = "")
        },
        actions = {
            IconButton(
                onClick = {
                    //Toast.makeText(context, "Handle favorite here", Toast.LENGTH_SHORT).show()
                    mainViewModel.insertFavoriteRecipe()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "",
                    tint = bottomNavigationOrange
                )
            }
        },
        backgroundColor = Color.White
    )
}