package fi.lauriari.recipe_app.screens.searchresult

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
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

    Column {
        Text("Hello search result!")
        Button(onClick = {
            Toast.makeText(context, mainViewModel.searchTextState.value, Toast.LENGTH_SHORT).show()
            Log.d("datalog", "${mainViewModel.getData.value?.body()}")
        }) {
            Text(text = "Show items in logcat")
        }
    }

}