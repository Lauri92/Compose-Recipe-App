package fi.lauriari.recipe_app.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange

@Composable
fun SelectedFilterItem(
    selectedFilterValue: String,
    onResetType: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .padding(top = 5.dp)
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        onClick = {},
        contentPadding = PaddingValues(),
        border = BorderStroke(
            width = 1.dp,
            color = Color.White
        ),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = BottomNavOrange,
            contentColor = Color.White
        ),
    ) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp),
            text = selectedFilterValue,
        )
        IconButton(
            onClick = { onResetType() })
        {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Close,
                contentDescription = "Expand or minimize arrow",
                tint = Color.White
            )
        }
    }
}