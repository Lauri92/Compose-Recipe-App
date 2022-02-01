package fi.lauriari.recipe_app.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterDropDown(
    filterArray: List<String>,
    onSelectedFilterType: (String) -> Unit,
    filterType: String,
) {

    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    var parentSize by remember { mutableStateOf(IntSize.Zero) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                parentSize = it.size
            }
            .background(MaterialTheme.colors.background)
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            fontSize = 17.sp,
            modifier = Modifier
                .padding(8.dp)
                .weight(8f),
            text = filterType,
            style = MaterialTheme.typography.subtitle2
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(degrees = angle)
                .weight(weight = 1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Arrow down icon"
            )
        }
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { parentSize.width.toDp() })
                .height(250.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            filterArray.forEach { filterArrayItem ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelectedFilterType(filterArrayItem)
                    })
                {
                    FilterItem(
                        filterItemName = filterArrayItem,
                        imageVector = Icons.Filled.Favorite
                    )
                }

            }
        }
    }
}

@Composable
@Preview
fun FilterDropDownPreview() {
    val list = listOf("American", "Asian", "British")
    FilterDropDown(
        filterArray = list,
        onSelectedFilterType = {},
        filterType = "cuisineType",
    )
}