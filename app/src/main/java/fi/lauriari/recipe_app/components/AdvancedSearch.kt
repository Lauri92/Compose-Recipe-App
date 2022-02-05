package fi.lauriari.recipe_app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.ui.theme.BottomNavGray
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.ui.theme.FocusedSearchBackgroundColor
import fi.lauriari.recipe_app.ui.theme.UnfocusedSeachBackgroundColor
import fi.lauriari.recipe_app.util.Constants

@Composable
fun AdvancedSearch() {
    var advancedSearchExpanded by remember { mutableStateOf(false) }
    val searchbarBgColor by animateColorAsState(
        if (advancedSearchExpanded)
            FocusedSearchBackgroundColor else UnfocusedSeachBackgroundColor
    )
    val searchbarTextColor by animateColorAsState(
        if (advancedSearchExpanded)
            BottomNavOrange else BottomNavGray
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 2.dp)
            .background(searchbarBgColor)
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
            )
            {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    color = searchbarTextColor,
                    text = "Advanced Search"
                )
                if (advancedSearchExpanded) {
                    AdvancedSearchDropdownItem(Constants.CUISINE_TYPES, "Cuisine", 200.dp)
                    AdvancedSearchDropdownItem(Constants.MEAL_TYPES, "Meal", 150.dp)
                    AdvancedSearchDropdownItem(Constants.DISH_TYPES, "Dish", 200.dp)
                }
            }

            IconButton(
                onClick = { advancedSearchExpanded = !advancedSearchExpanded })
            {
                Icon(
                    imageVector = if (advancedSearchExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Expand or minimize arrow"
                )
            }
        }
    }
}