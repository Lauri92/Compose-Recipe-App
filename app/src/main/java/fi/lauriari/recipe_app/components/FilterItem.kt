package fi.lauriari.recipe_app.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.lauriari.recipe_app.ui.theme.Typography

@Composable
fun FilterItem(
    filterItemName: String,
    imageVector: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp),
            imageVector = imageVector,
            contentDescription = "Search icon",
            tint = Color.Black
        )

        Text(
            modifier = Modifier
                .padding(8.dp),
            text = filterItemName,
            style = Typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
fun FilterItemPreview() {
    FilterItem(
        filterItemName = "Non diary",
        imageVector = Icons.Filled.Search,
    )
}