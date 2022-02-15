package fi.lauriari.recipe_app.screens.search

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import fi.lauriari.recipe_app.components.AdvancedSearch
import fi.lauriari.recipe_app.components.FoodSearchBar
import fi.lauriari.recipe_app.components.SelectedFiltersRow
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel

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

    val ai: ApplicationInfo = LocalContext.current.packageManager
        .getApplicationInfo(LocalContext.current.packageName, PackageManager.GET_META_DATA)
    val appId = ai.metaData["appIdValue"]
    val appKey = ai.metaData["appKeyValue"]
    val appIdValue = appId.toString()
    val appKeyValue = appKey.toString()

    val searchData by mainViewModel.searchData.collectAsState()

    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() }
                )
            },
    ) {

        FoodSearchBar(
            mainViewModel = mainViewModel,
            searchTextState = searchTextState,
            appIdValue = appIdValue,
            appKeyValue = appKeyValue
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

        BottomContent(
            searchData = searchData,
            mainViewModel = mainViewModel,
            appIdValue = appIdValue,
            appKeyValue = appKeyValue
        )

    }
}

@Composable
fun BottomContent(
    searchData: APIRequestState<EdamamSearchResult>,
    mainViewModel: MainViewModel,
    appIdValue: String,
    appKeyValue: String
) {

    val context = LocalContext.current
    when (searchData) {
        is APIRequestState.Success -> {
            if (searchData.responseValue.hits.isNotEmpty()) {
                ShowRecipes(
                    hits = searchData.responseValue.hits,
                    mainViewModel = mainViewModel,
                    appIdValue = appIdValue,
                    appKeyValue = appKeyValue
                )
            }
        }
        is APIRequestState.EmptyList -> {
            Text(text = "No hits")
        }
        is APIRequestState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(all = 25.dp)
                        .size(200.dp),
                    color = BottomNavOrange,
                    strokeWidth = 10.dp
                )
            }
        }
        is APIRequestState.Error -> {
            Text(text = "Error loading recipes")
        }
        is APIRequestState.Idle -> {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 150.dp),
                text = "Find what your heart and stomach desire..",
                fontSize = 36.sp
            )
        }
        is APIRequestState.BadResponse -> {
            Toast.makeText(context, "Error fetching recipes", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShowRecipes(
    hits: List<Hits>,
    mainViewModel: MainViewModel,
    appIdValue: String,
    appKeyValue: String
) {
    val context = LocalContext.current

    val nextpageSearchData by mainViewModel.nextpageSearchData.collectAsState()

    val listState = rememberLazyListState()

    var visibleButtonIndex by remember { mutableStateOf(6) }

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > visibleButtonIndex
        }
    }

    val recipeList by remember { mutableStateOf(hits.toMutableList()) }

    when (nextpageSearchData) {
        is APIRequestState.Success -> {
            val data = nextpageSearchData as APIRequestState.Success<EdamamSearchResult>
            if (data.responseValue.hits.isNotEmpty()) {
                recipeList.addAll(data.responseValue.hits)
                visibleButtonIndex += 10
                mainViewModel.setNextSearchPageStatusIdle()
            }
        }
        is APIRequestState.EmptyList -> {
            Toast.makeText(context, "EMPTY LIST STATE", Toast.LENGTH_SHORT).show()
            mainViewModel.setNextSearchPageStatusIdle()
        }
        is APIRequestState.Error -> {
            Toast.makeText(context, "ERROR state", Toast.LENGTH_SHORT).show()
            mainViewModel.setNextSearchPageStatusIdle()
        }
        is APIRequestState.BadResponse -> {
            Toast.makeText(context, "BAD RESPONSE state", Toast.LENGTH_SHORT).show()
            mainViewModel.setNextSearchPageStatusIdle()
        }
        is APIRequestState.Idle -> {

        }
        is APIRequestState.Loading -> {

        }
    }

    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter

    ) {

        LazyVerticalGrid(
            //modifier = Modifier.weight(10f),
            state = listState,
            cells = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(
                start = 21.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
        ) {
            items(
                items = recipeList,
            ) { hit ->
                SingleRecipe(hit.recipe)
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            Box(
                Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        mainViewModel.getNextPageRecipes(
                            appIdValue = appIdValue,
                            appKeyValue = appKeyValue,
                        )
                    }
                ) {
                    Text("Load more recipes")
                }
            }
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
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(
                data = recipe.image,
                builder = {
                    //placeholder()
                    //error()
                    crossfade(500)
                    transformations(
                        CircleCropTransformation()
                    )
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
