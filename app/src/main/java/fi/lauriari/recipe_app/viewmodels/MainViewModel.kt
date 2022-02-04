package fi.lauriari.recipe_app.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.repository.RecipeRepository
import fi.lauriari.recipe_app.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()

    val searchTextState: MutableState<String> = mutableStateOf("")
    val cuisineType: MutableState<String> = mutableStateOf("Cuisine Type")
    val mealType: MutableState<String> = mutableStateOf("Meal Type")
    val dishType: MutableState<String> = mutableStateOf("Dish Type")

    private var _sampleData =
        MutableStateFlow<RequestState<Response<EdamamSearchResult>>>(RequestState.Idle)
    val sampleData: StateFlow<RequestState<Response<EdamamSearchResult>>> = _sampleData

    fun getSomeDataFromApi(appIdValue: String, appKeyValue: String) {
        _sampleData.value = RequestState.Loading
        try {
            viewModelScope.launch {
                recipeRepository.getSomeDataFromApi(appIdValue, appKeyValue).collect {
                    Log.d("flowtry", it.body().toString())
                    _sampleData.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _sampleData.value = RequestState.Error(e)
        }
    }

}