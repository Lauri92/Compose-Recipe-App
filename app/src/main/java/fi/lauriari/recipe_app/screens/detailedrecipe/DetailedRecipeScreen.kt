package fi.lauriari.recipe_app.screens.detailedrecipe

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.viewmodels.MainViewModel

@Composable
fun DetailedRecipeScreen(
    mainViewModel: MainViewModel,
    isRecipeFavorited: Boolean,
    navigateToSearchScreen: () -> Unit
) {

    Scaffold(
        topBar = {
            SingleRecipeTopBar(
                isRecipeFavorited = isRecipeFavorited,
                navigateToSearchScreen = navigateToSearchScreen,
                onInsertFavorite = {
                    mainViewModel.insertFavoriteRecipe(
                        activeRecipe = FavoriteRecipe(
                            id = mainViewModel.selectedRecipe?.uri?.substringAfter(
                                "recipe_"
                            ).toString(),
                            label = mainViewModel.selectedRecipe?.label.toString(),
                            imageUrl = mainViewModel.selectedRecipe?.image.toString(),
                            instructionsUrl = mainViewModel.selectedRecipe?.url.toString()
                        ),
                        ingredientLines = mainViewModel.selectedRecipe!!.ingredientLines
                    )

                },
                onRemoveFavorite = {
                    mainViewModel.deleteFavoriteRecipe(
                        mainViewModel.selectedRecipe?.uri?.substringAfter("recipe_").toString()
                    )
                }
            )

        },
        content = {
            DetailedRecipeContent(
                mainViewModel = mainViewModel
            )
        },
    )
}


