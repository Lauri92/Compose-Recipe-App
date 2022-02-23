package fi.lauriari.recipe_app.navigation

import androidx.navigation.NavController
import fi.lauriari.recipe_app.util.Constants.FAVORITES_SCREEN
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN


class Screens(navController: NavController) {
    val search: () -> Unit = {
        navController.navigate(route = "search") {
            popUpTo(SEARCH_SCREEN) { inclusive = true }
        }
    }

    val detailedRecipe: () -> Unit = {
        navController.navigate(route = "detailedrecipe")
    }

    val favorites: () -> Unit = {
        navController.navigate(route = "favorites") {
            popUpTo(FAVORITES_SCREEN) { inclusive = true }
        }
    }

    val detailedFavorite: (String) -> Unit = { favoriteId ->
        navController.navigate(route = "detailedfavorite/$favoriteId")
    }

}