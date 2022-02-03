package fi.lauriari.recipe_app.screens.searchresult

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import fi.lauriari.recipe_app.data.model.SearchResult
import fi.lauriari.recipe_app.util.RequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import retrofit2.Response


@Composable
fun SearchResultScreen(
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current

    val sampleData by mainViewModel.sampleData.collectAsState()

    Column {
        Text("Hello search result!")
        Button(onClick = {
            Toast.makeText(context, mainViewModel.searchTextState.value, Toast.LENGTH_SHORT)
                .show()
            Log.d("datalog", "${mainViewModel.sampleData.value}")
        }) {
            Text(text = "Show items in logcat")
        }
        if (sampleData is RequestState.Success) {
            if ((sampleData as RequestState.Success<Response<SearchResult>>).data.isSuccessful) {
                Text(text = "Was success!")
            } else {
                Text(text = "Failed!")
            }
        } else if (sampleData is RequestState.Loading) {
            Text(text = "Still loading...")
        }
    }

}