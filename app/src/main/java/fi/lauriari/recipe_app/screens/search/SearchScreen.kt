package fi.lauriari.recipe_app.screens.search


import androidx.compose.foundation.lazy.LazyListState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import androidx.compose.material.*
import androidx.compose.runtime.*
import fi.lauriari.recipe_app.components.BottomNavBar


@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    navigateToDetailedRecipeScreen: () -> Unit,
    listState: LazyListState,
    navigateToFavoritesScreen: () -> Unit,
) {

    val searchTextState: String by mainViewModel.searchNewRecipesTextState


    Scaffold(
        bottomBar = {
            BottomNavBar(
                navigateToSearchScreen = {},
                navigateToFavoritesScreen = navigateToFavoritesScreen,
                mainViewModel = mainViewModel
            )
        },
        content = {
            SearchScreenContent(
                navigateToDetailedRecipeScreen = navigateToDetailedRecipeScreen,
                listState = listState,
                mainViewModel = mainViewModel,
                searchTextState = searchTextState,
                onCuisineTypeSelected = {
                    mainViewModel.cuisineType.value = it
                },
                onResetCuisineType = {
                    mainViewModel.cuisineType.value = null
                },
                onMealTypeSelected = {
                    mainViewModel.mealType.value = it
                },
                onResetMealType = {
                    mainViewModel.mealType.value = null
                },
                onDishTypeSelected = {
                    mainViewModel.dishType.value = it
                },
                onResetDishType = {
                    mainViewModel.dishType.value = null
                }
            )
        }
    )
}
