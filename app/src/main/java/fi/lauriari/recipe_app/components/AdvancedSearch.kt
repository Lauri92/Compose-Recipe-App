package fi.lauriari.recipe_app.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.ui.theme.BottomNavGray
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.ui.theme.FocusedSearchBackgroundColor
import fi.lauriari.recipe_app.ui.theme.UnfocusedSearchBackgroundColor
import fi.lauriari.recipe_app.util.Constants

@Composable
fun AdvancedSearch(
    onCuisineTypeSelected: (String) -> Unit,
    onMealTypeSelected: (String) -> Unit,
    onDishTypeSelected: (String) -> Unit
) {
    var advancedSearchExpanded by remember { mutableStateOf(false) }

    val angle: Float by animateFloatAsState(
        if (advancedSearchExpanded) 180f else 0f
    )
    val searchbarBgColor by animateColorAsState(
        if (advancedSearchExpanded)
            FocusedSearchBackgroundColor else UnfocusedSearchBackgroundColor
    )
    val searchbarTextColor by animateColorAsState(
        if (advancedSearchExpanded)
            BottomNavOrange else BottomNavGray
    )

    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .border(2.dp, if (advancedSearchExpanded) BottomNavOrange else Color.Transparent)
            .verticalScroll(rememberScrollState())
            .background(searchbarBgColor)
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 15.dp,
                        top = 15.dp,
                        bottom = if (advancedSearchExpanded) 40.dp else 15.dp
                    )
            )
            {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    color = searchbarTextColor,
                    text = "Advanced Search"
                )
                if (advancedSearchExpanded) {
                    AdvancedSearchDropdownItem(
                        onFilterItemSelected = onCuisineTypeSelected,
                        filterItemArray = Constants.CUISINE_TYPES,
                        label = "Cuisine",
                        dropdownItemHeight = 200.dp,
                        topPadding = 15.dp
                    )
                    AdvancedSearchDropdownItem(
                        onFilterItemSelected = onMealTypeSelected,
                        filterItemArray = Constants.MEAL_TYPES,
                        label = "Meal",
                        dropdownItemHeight = 150.dp,
                        topPadding = 0.dp
                    )
                    AdvancedSearchDropdownItem(
                        onFilterItemSelected = onDishTypeSelected,
                        filterItemArray = Constants.DISH_TYPES,
                        label = "Dish",
                        dropdownItemHeight = 200.dp,
                        topPadding = 0.dp
                    )
                }
            }

            IconButton(
                modifier = Modifier
                    .rotate(degrees = angle),
                onClick = { advancedSearchExpanded = !advancedSearchExpanded })
            {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand or minimize arrow"
                )
            }
        }
    }
}