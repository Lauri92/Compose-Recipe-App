package fi.lauriari.recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fi.lauriari.recipe_app.navigation.InitNavigation
import fi.lauriari.recipe_app.ui.theme.RecipeAppTheme
import fi.lauriari.recipe_app.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                navController = rememberNavController()
                InitNavigation(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
