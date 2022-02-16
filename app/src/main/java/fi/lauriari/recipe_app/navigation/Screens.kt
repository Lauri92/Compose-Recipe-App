package fi.lauriari.recipe_app.navigation

import androidx.navigation.NavController
import fi.lauriari.recipe_app.util.Constants.MAINMENU_SCREEN

class Screens(navController: NavController) {
    val search: () -> Unit = {
        navController.navigate(route = "search")
    }
}