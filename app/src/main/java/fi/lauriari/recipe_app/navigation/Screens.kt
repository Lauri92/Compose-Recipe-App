package fi.lauriari.recipe_app.navigation

import androidx.navigation.NavController

class Screens(navController: NavController) {
    val mainmenu: () -> Unit = {
        navController.navigate(route = "search")
    }
}