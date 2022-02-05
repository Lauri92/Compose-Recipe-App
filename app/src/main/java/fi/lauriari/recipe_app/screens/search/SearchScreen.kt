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
    val cuisineType: String by mainViewModel.cuisineType
    val mealType: String by mainViewModel.mealType
    val dishType: String by mainViewModel.dishType


    Scaffold(
        bottomBar = {
            BottomNavBar()
        },
        content = {
            SearchScreenContent(
                mainViewModel = mainViewModel,
                searchTextState = searchTextState,
                cuisineType = cuisineType,
                onCuisineTypeSelected = {
                    mainViewModel.cuisineType.value = it
                },
                mealType = mealType,
                onMealTypeSelected = {
                    mainViewModel.mealType.value = it
                },
                dishType = dishType,
                onDishTypeSelected = {
                    mainViewModel.dishType.value = it
                }
            )
        }
    )
}
