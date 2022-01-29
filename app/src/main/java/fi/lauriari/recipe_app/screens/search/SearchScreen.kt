package fi.lauriari.recipe_app.screens.search


import fi.lauriari.recipe_app.viewmodels.MainViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.runtime.Composable


@Composable
fun SearchScreen(
    navigateToMainMenuScreen: () -> Unit,
    mainViewModel: MainViewModel
) {

    BackHandler {
        navigateToMainMenuScreen()
    }

    Text(text = "HELLO!!")

}
