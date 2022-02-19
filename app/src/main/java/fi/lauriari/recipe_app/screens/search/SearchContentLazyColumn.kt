package fi.lauriari.recipe_app.screens.search

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.ui.theme.BottomNavOrange
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchContentLazyColumn(
    searchData: APIRequestState<EdamamSearchResult>,
    mainViewModel: MainViewModel,
    navigateToDetailedRecipeScreen: () -> Unit,
) {

    val context = LocalContext.current
    when (searchData) {
        is APIRequestState.Success -> {
            if (searchData.responseValue.hits.isNotEmpty()) {
                ShowRecipes(
                    navigateToDetailedRecipeScreen = navigateToDetailedRecipeScreen,
                    hits = searchData.responseValue.hits as MutableList<Hits>,
                    mainViewModel = mainViewModel,
                )
            }
        }
        is APIRequestState.EmptyList -> {
            mainViewModel.visibleButtonIndex.value = 6
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

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ShowRecipes(
    hits: MutableList<Hits>,
    mainViewModel: MainViewModel,
    navigateToDetailedRecipeScreen: () -> Unit,
) {
    val context = LocalContext.current

    mainViewModel.recipeList.value = hits

    val nextpageSearchData by mainViewModel.nextpageSearchData.collectAsState()

    val listState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > mainViewModel.visibleButtonIndex.value
        }
    }

    when (nextpageSearchData) {
        is APIRequestState.Success -> {
            val data = nextpageSearchData as APIRequestState.Success<EdamamSearchResult>
            if (data.responseValue.hits.isNotEmpty()) {
                mainViewModel.recipeList.value.addAll(data.responseValue.hits)
                mainViewModel.visibleButtonIndex.value += 10
                mainViewModel.setNextSearchPageStatusIdle()
                coroutineScope.launch {
                    listState.animateScrollToItem(mainViewModel.visibleButtonIndex.value - 6)
                }
                mainViewModel.buttonEnabled.value = true
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
                items = mainViewModel.recipeList.value,
            ) { hit ->
                SingleRecipe(
                    recipe = hit.recipe,
                    navigateToDetailedRecipeScreen = navigateToDetailedRecipeScreen,
                    mainViewModel = mainViewModel
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 500
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 500
                )
            )
        ) {
            Box(
                Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    enabled = mainViewModel.buttonEnabled.value,
                    onClick = {
                        mainViewModel.getNextPageRecipes()
                        mainViewModel.buttonEnabled.value = false
                    }
                ) {
                    Text("Load more")
                }
            }
        }
    }
}

@Composable
fun SingleRecipe(
    recipe: Recipe,
    mainViewModel: MainViewModel,
    navigateToDetailedRecipeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                mainViewModel.selectedRecipe = recipe
                navigateToDetailedRecipeScreen()
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
