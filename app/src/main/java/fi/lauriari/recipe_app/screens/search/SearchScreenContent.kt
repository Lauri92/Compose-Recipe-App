package fi.lauriari.recipe_app.screens.search

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import fi.lauriari.recipe_app.components.SelectedFiltersRow
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
    onResetDishType: () -> Unit,
    navigateToDetailedRecipeScreen: () -> Unit,
    listState: LazyListState
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

        SearchContentLazyColumn(
            searchData = searchData,
            mainViewModel = mainViewModel,
            navigateToDetailedRecipeScreen = navigateToDetailedRecipeScreen,
            listState = listState
        )

    }
}
