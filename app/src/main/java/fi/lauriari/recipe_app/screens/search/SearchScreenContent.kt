package fi.lauriari.recipe_app.screens.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.R
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
    searchTextState: String
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
            filterArrayLabel = "Cuisine Type"
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = MEAL_TYPES,
            filterArrayLabel = "Meal Type"
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = DISH_TYPES,
            filterArrayLabel = "Dish type"
        )

    }
}