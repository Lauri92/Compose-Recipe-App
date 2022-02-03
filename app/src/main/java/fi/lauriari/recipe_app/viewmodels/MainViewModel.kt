package fi.lauriari.recipe_app.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.lauriari.recipe_app.data.model.SearchResult
import fi.lauriari.recipe_app.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val recipeRepository = RecipeRepository()

    val searchTextState: MutableState<String> = mutableStateOf("")
    val cuisineType: MutableState<String> = mutableStateOf("Cuisine Type")
    val mealType: MutableState<String> = mutableStateOf("Meal Type")
    val dishType: MutableState<String> = mutableStateOf("Dish Type")


    var getData: MutableLiveData<Response<SearchResult>> = MutableLiveData()
    fun getSomeDataFromApi(appIdValue: String, appKeyValue: String) {
        viewModelScope.launch {
            val data = recipeRepository.getSomeDataFromApi(appIdValue, appKeyValue)
            getData.value = data
        }
    }

}