package fi.lauriari.recipe_app.components

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.lauriari.recipe_app.ui.theme.*

@Composable
fun AdvancedSearchDropdownItem(
    onFilterItemSelected: (String) -> Unit,
    filterItemArray: List<String>,
    label: String,
    dropdownItemHeight: Dp,
    topPadding: Dp
) {
    var subMenuExpanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(top = topPadding, start = 20.dp)
            .border(0.5.dp, SearchBarTextColor)
            .fillMaxWidth()
            .background(
                if (subMenuExpanded)
                    ExpandedAdvancedSearchItem else UnfocusedSearchBackgroundColor
            ),
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = label,
                )
                if (subMenuExpanded) {
                    LazyColumn(
                        modifier = Modifier
                            .height(dropdownItemHeight)
                            .padding(vertical = 4.dp),
                    ) {
                        items(
                            items = filterItemArray
                        ) { filterItem ->
                            Row(
                                modifier = Modifier.clickable {
                                    onFilterItemSelected(filterItem)
                                },
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                                    text = filterItem,
                                )
                            }
                        }
                    }
                }
            }
            IconButton(
                onClick = { subMenuExpanded = !subMenuExpanded })
            {
                Icon(
                    imageVector = if (subMenuExpanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Expand or minimize arrow",
                    tint = BottomNavOrange
                )
            }
        }
    }
}