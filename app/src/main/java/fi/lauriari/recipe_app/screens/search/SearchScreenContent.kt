package fi.lauriari.recipe_app.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.components.FilterDropDown
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.util.Constants.CUISINE_TYPES
import fi.lauriari.recipe_app.util.Constants.DISH_TYPES
import fi.lauriari.recipe_app.util.Constants.MEAL_TYPES
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    mainViewModel: MainViewModel,
    navigateToSearchResultScreen: () -> Unit,
    searchTextState: String,
    cuisineType: String,
    onCuisineTypeSelected: (String) -> Unit,
    mealType: String,
    onMealTypeSelected: (String) -> Unit,
    dishType: String,
    onDishTypeSelected: (String) -> Unit,
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(all = 12.dp)
    ) {
        FoodSearchBar(
            context = context,
            mainViewModel = mainViewModel,
            navigateToSearchResultScreen = navigateToSearchResultScreen,
            searchTextState = searchTextState,
            focusManager = focusManager
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )

        FilterDropDown(
            filterArray = CUISINE_TYPES,
            filterType = cuisineType,
            onSelectedFilterType = onCuisineTypeSelected
        )

        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = MEAL_TYPES,
            filterType = mealType,
            onSelectedFilterType = onMealTypeSelected
        )

        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = DISH_TYPES,
            filterType = dishType,
            onSelectedFilterType = onDishTypeSelected
        )

    }
}