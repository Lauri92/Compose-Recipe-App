package fi.lauriari.recipe_app.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import fi.lauriari.recipe_app.R
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FoodSearchBar(
    context: Context,
    mainViewModel: MainViewModel,
    searchTextState: String,
    focusManager: FocusManager
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
                    val values = "Cuisine type: ${mainViewModel.cuisineType.value} \n" +
                            "Meal type: ${mainViewModel.mealType.value} \n" +
                            "Dish type: ${mainViewModel.dishType.value}\n" +
                            "Search text value: $searchTextState"
                    Toast.makeText(context, values, Toast.LENGTH_SHORT).show()
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
                val values = "Cuisine type: ${mainViewModel.cuisineType.value} \n" +
                        "Meal type: ${mainViewModel.mealType.value} \n" +
                        "Dish type: ${mainViewModel.dishType.value}\n" +
                        "Search text value: $searchTextState"
                Toast.makeText(context, values, Toast.LENGTH_SHORT).show()
                focusManager.clearFocus()
            },
        )
    )

}