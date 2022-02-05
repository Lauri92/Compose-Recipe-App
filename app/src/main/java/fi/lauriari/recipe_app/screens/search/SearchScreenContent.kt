package fi.lauriari.recipe_app.screens.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.components.AdvancedSearch
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.ui.theme.BottomNavGray
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.ui.theme.FocusedSearchBackgroundColor
import fi.lauriari.recipe_app.ui.theme.UnfocusedSeachBackgroundColor
import fi.lauriari.recipe_app.util.Constants.CUISINE_TYPES
import fi.lauriari.recipe_app.util.Constants.DISH_TYPES
import fi.lauriari.recipe_app.util.Constants.MEAL_TYPES
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
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 50.dp)
            .fillMaxSize(),
    ) {

        FoodSearchBar(
            mainViewModel = mainViewModel,
            searchTextState = searchTextState
        )

        AdvancedSearch()
/*
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
        */
    }
}
