package fi.lauriari.recipe_app.screens.search


import fi.lauriari.recipe_app.viewmodels.MainViewModel
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
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
            var selectedItem by remember { mutableStateOf(0) }
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                BottomNavigationItem(
                    selectedContentColor = bottomNavigationOrange,
                    unselectedContentColor = bottomNavigationGray,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search bottom nav item",
                        )
                    },
                    label = {
                        Text(
                            text = "Find",
                        )
                    },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )
                BottomNavigationItem(
                    selectedContentColor = bottomNavigationOrange,
                    unselectedContentColor = bottomNavigationGray,
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "My Recipes bottom nav item",
                        )
                    },
                    label = {
                        Text(
                            text = "My Recipes",
                        )
                    },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 })
            }
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
