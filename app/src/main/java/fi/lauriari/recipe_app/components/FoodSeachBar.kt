package fi.lauriari.recipe_app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.R
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.ui.theme.FocusedSearchBackgroundColor
import fi.lauriari.recipe_app.ui.theme.SearchBarTextColor
import fi.lauriari.recipe_app.ui.theme.UnfocusedSearchBackgroundColor
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun FoodSearchBar(
    searchTextState: String,
    onValueChange: (String) -> Unit,
    onSearchPressed: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    var isSearchbarFocused by remember { mutableStateOf(false) }
    val searchbarBgColor by animateColorAsState(
        if (isSearchbarFocused)
            FocusedSearchBackgroundColor else UnfocusedSearchBackgroundColor
    )

    OutlinedTextField(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 5.dp)
            .clip(
                shape = RoundedCornerShape(3.dp)
            )
            .background(searchbarBgColor)
            .onFocusChanged {
                isSearchbarFocused = it.isFocused
            }
            .fillMaxWidth(),
        value = searchTextState,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Black,
            focusedBorderColor = BottomNavOrange,
            unfocusedBorderColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_for_a_recipe_ph),
                color = SearchBarTextColor
            )
        },
        onValueChange = { newText ->
            onValueChange(newText)
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        ),
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    focusManager.clearFocus()
                    onSearchPressed()
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
                onSearchPressed()
                focusManager.clearFocus()
            },
        )
    )

}