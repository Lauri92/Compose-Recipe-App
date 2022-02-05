package fi.lauriari.recipe_app.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.lauriari.recipe_app.R
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    mainViewModel: MainViewModel,
    //navigateToSearchResultScreen: () -> Unit,
    searchTextState: String,
    cuisineType: String,
    onCuisineTypeSelected: (String) -> Unit,
    mealType: String,
    onMealTypeSelected: (String) -> Unit,
    dishType: String,
    onDishTypeSelected: (String) -> Unit,
) {
/*
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
    */


    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 25.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FoodSearchBar(
            mainViewModel = mainViewModel,
            searchTextState = searchTextState
        )


        Text(
            text = "Find what your heart and stomach desire..",
            fontSize = 36.sp
        )

        Image(
            painterResource(R.drawable.newbackgroundimage),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun NewSearchBar() {

}
