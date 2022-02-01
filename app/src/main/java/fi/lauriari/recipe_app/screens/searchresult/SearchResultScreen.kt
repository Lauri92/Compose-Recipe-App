package fi.lauriari.recipe_app.screens.searchresult

import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import fi.lauriari.recipe_app.viewmodels.MainViewModel


@Composable
fun SearchResultScreen(
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    Text("Hello search result!")
    Button(onClick = {
        Toast.makeText(context, mainViewModel.searchTextState.value, Toast.LENGTH_SHORT).show()
    }) {
    }
}