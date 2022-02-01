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
    val cuisineType: String by mainViewModel.cuisineType
    val mealType: String by mainViewModel.mealType
    val dishType: String by mainViewModel.dishType

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
