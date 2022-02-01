package fi.lauriari.recipe_app.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    val searchTextState: MutableState<String> = mutableStateOf("")
    val cuisineType: MutableState<String> = mutableStateOf("Cuisine Type")
    val mealType: MutableState<String> = mutableStateOf("Meal Type")
    val dishType: MutableState<String> = mutableStateOf("Dish Type")

}