package fi.lauriari.recipe_app.screens.search


import fi.lauriari.recipe_app.viewmodels.MainViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


@Composable
fun SearchScreen(
    navigateToMainMenuScreen: () -> Unit,
    mainViewModel: MainViewModel
) {
    BackHandler {
        navigateToMainMenuScreen()
    }

    val searchTextState: String by mainViewModel.searchTextState

    SearchScreenContent(
        mainViewModel = mainViewModel,
        searchTextState = searchTextState
    )

}
