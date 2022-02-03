package fi.lauriari.recipe_app.navigation.destinations

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fi.lauriari.recipe_app.screens.searchresult.SearchResultScreen
import fi.lauriari.recipe_app.util.Constants.SEARCH_RESULT_SCREEN
import fi.lauriari.recipe_app.viewmodels.MainViewModel

fun NavGraphBuilder.searchResultComposable(mainViewModel: MainViewModel) {
    composable(
        route = SEARCH_RESULT_SCREEN
    ) { navBackStackEntry ->

        //val searchResult by mainViewModel.searchResult.collectAsState()
        SearchResultScreen(
            mainViewModel = mainViewModel
        )
    }
}