package fi.lauriari.recipe_app.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import fi.lauriari.recipe_app.screens.detailedfavorite.DetailedFavoriteScreen
import fi.lauriari.recipe_app.util.Constants.DETAILED_FAVORITE_KEY
import fi.lauriari.recipe_app.util.Constants.DETAILED_FAVORITE_SCREEN

fun NavGraphBuilder.detailedFavoriteComposable(

) {
    composable(
        route = DETAILED_FAVORITE_SCREEN,
        arguments = listOf(navArgument(DETAILED_FAVORITE_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        val selectedFavorite = navBackStackEntry.arguments!!.getString(DETAILED_FAVORITE_KEY)
        Log.d("favoritetest", selectedFavorite.toString())

        DetailedFavoriteScreen()

    }
}