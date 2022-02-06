package fi.lauriari.recipe_app.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import fi.lauriari.recipe_app.components.AdvancedSearch
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.components.SelectedFiltersRow
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import retrofit2.Response

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenContent(
    mainViewModel: MainViewModel,
    searchTextState: String,
    onCuisineTypeSelected: (String) -> Unit,
    onResetCuisineType: () -> Unit,
    onMealTypeSelected: (String) -> Unit,
    onResetMealType: () -> Unit,
    onDishTypeSelected: (String) -> Unit,
    onResetDishType: () -> Unit
) {

    val sampleData by mainViewModel.sampleData.collectAsState()

    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 50.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
    ) {

        FoodSearchBar(
            mainViewModel = mainViewModel,
            searchTextState = searchTextState
        )

        AdvancedSearch(
            onCuisineTypeSelected = onCuisineTypeSelected,
            onMealTypeSelected = onMealTypeSelected,
            onDishTypeSelected = onDishTypeSelected,
        )
        SelectedFiltersRow(
            mainViewModel = mainViewModel,
            onResetCuisineType = onResetCuisineType,
            onResetMealType = onResetMealType,
            onResetDishType = onResetDishType
        )

        Column {
            when (sampleData) {
                is APIRequestState.Success -> {
                    val data = sampleData as APIRequestState.Success<Response<EdamamSearchResult>>
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
                is APIRequestState.Loading -> {
                    CircularProgressIndicator()
                    Text(text = "Still loading recipes...")
                }
                is APIRequestState.Error -> Text(text = "Error loading recipes")
                is APIRequestState.Idle -> {
                    Text(
                        modifier = Modifier
                            .padding(top = 150.dp),
                        text = "Find what your heart and stomach desire..",
                        fontSize = 36.sp
                    )
                }
            }
        }

/*
        Image(
            painterResource(R.drawable.newbackgroundimage),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
        */
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowRecipes(
    hits: List<Hits>
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(hits) { hit ->
            SingleRecipe(hit.recipe)
        }
    }
}

@Composable
fun SingleRecipe(
    recipe: Recipe
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .clickable {
                uriHandler.openUri(recipe.url)
            }
            .padding(all = 10.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Image(
                painter = rememberImagePainter(
                    data = recipe.image,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "Image of a recipe",
                modifier = Modifier.size(150.dp)
            )
            Text(
                modifier = Modifier
                    .padding(all = 2.dp),
                text = recipe.label
            )
        }
    }

}
