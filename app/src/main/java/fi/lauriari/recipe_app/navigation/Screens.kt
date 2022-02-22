package fi.lauriari.recipe_app.navigation

import androidx.navigation.NavController


class Screens(navController: NavController) {
    val search: () -> Unit = {
        navController.navigate(route = "search")
    }

    val detailedRecipe: () -> Unit = {
        navController.navigate(route = "detailedrecipe")
    }

    val favorites: () -> Unit = {
        navController.navigate(route = "favorites")
    }

    val detailedFavorite: (String) -> Unit = { favoriteId ->
        navController.navigate(route = "detailedfavorite/$favoriteId")
    }

}