package fi.lauriari.recipe_app.screens.search


import fi.lauriari.recipe_app.viewmodels.MainViewModel
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import fi.lauriari.recipe_app.components.BottomNavBar
import fi.lauriari.recipe_app.ui.theme.bottomNavigationGray
import fi.lauriari.recipe_app.ui.theme.bottomNavigationOrange


@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
) {

    val searchTextState: String by mainViewModel.searchTextState


    Scaffold(
        bottomBar = {
            BottomNavBar(mainViewModel = mainViewModel)
        },
        content = {
            SearchScreenContent(
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
