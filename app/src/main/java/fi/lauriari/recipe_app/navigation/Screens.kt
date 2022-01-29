package fi.lauriari.recipe_app.navigation

import androidx.navigation.NavController
import fi.lauriari.recipe_app.util.Constants.MAINMENU_SCREEN

class Screens(navController: NavController) {
    val mainmenu: () -> Unit = {
        navController.navigate(route = "search")
    }

    val search: () -> Unit = {
        navController.navigate(route = "mainmenu") {
            popUpTo(MAINMENU_SCREEN) { inclusive = true }
        }
    }
}