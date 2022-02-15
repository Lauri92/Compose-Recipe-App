package fi.lauriari.recipe_app.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.repository.RecipeRepository
import fi.lauriari.recipe_app.util.APIRequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()

    val searchTextState: MutableState<String> = mutableStateOf("")
    val cuisineType: MutableState<String> = mutableStateOf("")
    val mealType: MutableState<String> = mutableStateOf("")
    val dishType: MutableState<String> = mutableStateOf("")
    private var nextpageSearchQuery: String = ""
    private var nextpageContQuery: String = ""

    private var _searchData =
        MutableStateFlow<APIRequestState<EdamamSearchResult>>(APIRequestState.Idle)
    val searchData: StateFlow<APIRequestState<EdamamSearchResult>> = _searchData

    fun getDataByQuery(
        appIdValue: String,
        appKeyValue: String,
        searchQuery: String
    ) {
        _searchData.value = APIRequestState.Loading
        try {
            viewModelScope.launch {
                recipeRepository.getDataByQuery(
                    appIdValue = appIdValue,
                    appKeyValue = appKeyValue,
                    searchQuery = searchQuery
                ).collect { response ->
                    if (response.isSuccessful) {
                        if (response.body()!!.hits.isNotEmpty()) {
                            nextpageSearchQuery = searchQuery
                            nextpageContQuery = response.body()!!._links.next.href
                                .substringAfter("_cont=")
                                .substringBefore('%')
                            _searchData.value = APIRequestState.Success(response.body()!!)
                        } else {
                            _searchData.value = APIRequestState.EmptyList
                        }
                    }
                }
            }
        } catch (e: Exception) {
            _searchData.value = APIRequestState.Error(e)
        }
    }

    private var _nextpageSearchData =
        MutableStateFlow<APIRequestState<EdamamSearchResult>>(APIRequestState.Idle)
    val nextpageSearchData: StateFlow<APIRequestState<EdamamSearchResult>> =
        _nextpageSearchData

    fun getNextPageRecipes(
        appIdValue: String,
        appKeyValue: String,
    ) {
        _nextpageSearchData.value = APIRequestState.Loading
        try {
            viewModelScope.launch {
                recipeRepository.getMoreRecipesFromApi(
                    appIdValue = appIdValue,
                    appKeyValue = appKeyValue,
                    searchQuery = nextpageSearchQuery,
                    nextpageContQuery = nextpageContQuery
                ).collect { response ->
                    if (response.isSuccessful) {
                        if (response.body()!!.hits.isNotEmpty()) {
                            nextpageContQuery = response.body()!!._links.next.href
                                .substringAfter("_cont=")
                                .substringBefore('%')
                            _nextpageSearchData.value =
                                APIRequestState.Success(response.body()!!)
                        } else {
                            _nextpageSearchData.value = APIRequestState.EmptyList
                        }
                    } else {
                        _nextpageSearchData.value = APIRequestState.BadResponse
                    }
                }
            }
        } catch (e: Exception) {
            _nextpageSearchData.value = APIRequestState.Error(e)
        }
    }

    fun setNextSearchPageStatusIdle() {
        _nextpageSearchData.value = APIRequestState.Idle
    }

}