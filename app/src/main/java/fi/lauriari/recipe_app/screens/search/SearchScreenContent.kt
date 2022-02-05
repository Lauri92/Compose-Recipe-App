package fi.lauriari.recipe_app.screens.search

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.components.AdvancedSearch
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    mainViewModel: MainViewModel,
    searchTextState: String,
    onCuisineTypeSelected: (String) -> Unit,
    onMealTypeSelected: (String) -> Unit,
    onDishTypeSelected: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 50.dp)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
    ) {

        FoodSearchBar(
            mainViewModel = mainViewModel,
            searchTextState = searchTextState
        )

        AdvancedSearch(
            onCuisineTypeSelected = onCuisineTypeSelected,
            onMealTypeSelected = onMealTypeSelected,
            onDishTypeSelected = onDishTypeSelected
        )
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
