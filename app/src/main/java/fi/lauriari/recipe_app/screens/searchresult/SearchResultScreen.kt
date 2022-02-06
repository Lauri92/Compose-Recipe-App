package fi.lauriari.recipe_app.screens.searchresult

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import fi.lauriari.recipe_app.ui.theme.SearchBarTextColor
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.viewmodels.MainViewModel
import retrofit2.Response


@Composable
fun SearchResultScreen(
    mainViewModel: MainViewModel
) {
}