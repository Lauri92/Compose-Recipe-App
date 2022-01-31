package fi.lauriari.recipe_app.screens.search

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
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    mainViewModel: MainViewModel,
    searchTextState: String
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(all = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchTextState,
            label = { Text(text = stringResource(id = R.string.search)) },
            placeholder = {
                Text(text = stringResource(id = R.string.search_for_food_ph))
            },
            onValueChange = { newText ->
                mainViewModel.searchTextState.value = newText
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, searchTextState, Toast.LENGTH_SHORT).show()
                        focusManager.clearFocus()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Toast.makeText(context, searchTextState, Toast.LENGTH_SHORT).show()
                    focusManager.clearFocus()
                },
            )
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        val cuisineTypeArray = listOf(
            "American",
            "Asian",
            "British",
            "Caribbean",
            "Central Europea",
            "Chinese",
            "Eastern Europe",
            "French",
            "Indian",
            "Italin",
            "Japanese",
            "Kosher",
            "Mediterranean",
            "Mexican",
            "Middle Eastern",
            "Nordic",
            "South American",
            "South East Asian"

        )
        val mealTypeArray = listOf("Breakfast", "Dinner", "Lunch", "Snack", "Teatime")
        val dishTypeArray = listOf(
            "Biscuits and Cookies",
            "Breads", "Cereals", "Condiments and sauces",
            "Desserts", "Drinks", "Main Course",
            "Pancake", "Preps", "Preserve",
            "Salad", "Sandwiches", "Side dish",
            "Soup", "Starter", "Sweets"
        )
        FilterDropDown(
            filterArray = cuisineTypeArray,
            filterArrayLabel = "Cuisine Type"
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = mealTypeArray,
            filterArrayLabel = "Meal Type"
        )
        Divider(
            modifier = Modifier
                .height(8.dp),
            color = MaterialTheme.colors.background
        )
        FilterDropDown(
            filterArray = dishTypeArray,
            filterArrayLabel = "Dish type"
        )

    }

}