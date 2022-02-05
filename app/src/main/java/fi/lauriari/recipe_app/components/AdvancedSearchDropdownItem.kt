package fi.lauriari.recipe_app.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AdvancedSearchDropdownItem(filterType: List<String>, label: String, dropdownItemHeight: Dp) {
    var subMenuExpanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 20.dp)
            .fillMaxWidth()
            .background(Color.White),
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
                        modifier = androidx.compose.ui.Modifier
                            .height(dropdownItemHeight)
                            .padding(vertical = 4.dp),
                    ) {
                        items(items = filterType) { cuisine ->
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                text = cuisine,
                            )
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
                )
            }
        }
    }
}