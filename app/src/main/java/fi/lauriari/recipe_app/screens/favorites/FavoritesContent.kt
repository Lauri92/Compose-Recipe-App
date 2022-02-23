package fi.lauriari.recipe_app.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesContent(
    mainViewModel: MainViewModel,
    allFavoriteRecipes: State<List<RecipeWithIngredientLines>>,
    navigateToDetailedFavoriteScreen: (String) -> Unit,
    favoritesListState: LazyListState,
    searchFavoriteRecipesTextState: String
) {

    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
    ) {
        FoodSearchBar(
            placeholderText = "Search for a Favorite",
            searchTextState = searchFavoriteRecipesTextState,
            onValueChange = { newText ->
                mainViewModel.searchFavoriteRecipesTextState.value = newText
                if (mainViewModel.searchFavoriteRecipesTextState.value != "") {
                    mainViewModel.searchFavoriteRecipes(mainViewModel.searchFavoriteRecipesTextState.value)
                } else {
                    mainViewModel.getAllFavoriteRecipes()
                }
            },
            onSearchPressed = {
                if (mainViewModel.searchFavoriteRecipesTextState.value != "") {
                    mainViewModel.searchFavoriteRecipes(mainViewModel.searchFavoriteRecipesTextState.value)
                } else {
                    mainViewModel.getAllFavoriteRecipes()
                }
            }
        )

        FavoritesList(
            mainViewModel = mainViewModel,
            allFavoriteRecipes = allFavoriteRecipes,
            navigateToDetailedFavoriteScreen = navigateToDetailedFavoriteScreen,
            favoritesListState = favoritesListState
        )
    }
}