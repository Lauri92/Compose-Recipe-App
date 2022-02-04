package fi.lauriari.recipe_app.screens.searchresult

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.util.RequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import retrofit2.Response


@Composable
fun SearchResultScreen(
    mainViewModel: MainViewModel
) {
    // Observe state changes
    val sampleData by mainViewModel.sampleData.collectAsState()

    Column {
        when (sampleData) {
            is RequestState.Success -> {
                val data = sampleData as RequestState.Success<Response<EdamamSearchResult>>
                if (data.responseValue.isSuccessful) {
                    if (data.responseValue.body()!!.hits.isNotEmpty()) {
                        ShowRecipes(data.responseValue.body()!!.hits)
                    } else {
                        Text(text = "No hits")
                    }
                } else {
                    Text(text = "Failed!")
                }
            }
            is RequestState.Loading -> {
                CircularProgressIndicator()
                Text(text = "Still loading recipes...")
            }
            is RequestState.Error -> Text(text = "Error loading recipes")
            is RequestState.Idle -> {}
        }
    }
}

@Composable
fun ShowRecipes(
    hits: List<Hits>
) {
    LazyColumn {
        items(
            items = hits,
            key = { hit ->
                hit.recipe.uri
            }
        ) { hit ->
            SingleRecipe(hit.recipe)
        }
    }
}

@Composable
fun SingleRecipe(
    recipe: Recipe
) {
    val uriHandler = LocalUriHandler.current
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    uriHandler.openUri(recipe.url)
                }
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            Row() {
                Image(
                    painter = rememberImagePainter(
                        data = recipe.image,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Image of a recipe",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(all = 2.dp),
                    text = recipe.label
                )
            }
        }
    }
}