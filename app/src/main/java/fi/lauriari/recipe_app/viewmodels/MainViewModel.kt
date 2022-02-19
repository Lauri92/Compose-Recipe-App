package fi.lauriari.recipe_app.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.repository.RecipeRepository
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.util.Constants.BASE_URL_APPENDABLE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val recipeRepository = RecipeRepository()

    val searchTextState: MutableState<String> = mutableStateOf("")
    val cuisineType: MutableState<String?> = mutableStateOf(null)
    val mealType: MutableState<String?> = mutableStateOf(null)
    val dishType: MutableState<String?> = mutableStateOf(null)
    private var nextPageUrl: String = ""

    var recipeList: MutableState<MutableList<Hits>> = mutableStateOf(mutableListOf())
    var buttonEnabled: MutableState<Boolean> = mutableStateOf(true)
    var visibleButtonIndex: MutableState<Int> = mutableStateOf(6)

    var selectedRecipe: Recipe? = null

    private var _searchData =
        MutableStateFlow<APIRequestState<EdamamSearchResult>>(APIRequestState.Idle)
    val searchData: StateFlow<APIRequestState<EdamamSearchResult>> = _searchData

    fun getRecipesByQuery(
        appIdValue: String,
        appKeyValue: String,
        searchQuery: String
    ) {
        _searchData.value = APIRequestState.Loading
        try {
            viewModelScope.launch {
                recipeRepository.getRecipesByQuery(
                    appIdValue = appIdValue,
                    appKeyValue = appKeyValue,
                    searchQuery = searchQuery,
                    cuisineType = cuisineType.value,
                    mealType = mealType.value,
                    dishType = dishType.value
                ).collect { response ->
                    if (response.isSuccessful) {
                        if (response.body()!!.hits.isNotEmpty()) {
                            nextPageUrl =
                                BASE_URL_APPENDABLE + response.body()!!._links?.next?.href
                                    ?.substringAfter("2").toString()
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

    fun getNextPageRecipes() {
        _nextpageSearchData.value = APIRequestState.Loading
        try {
            viewModelScope.launch {
                recipeRepository.getMoreRecipes(
                    nextPageUrl = nextPageUrl,
                ).collect { response ->
                    if (response.isSuccessful) {
                        if (response.body()!!.hits.isNotEmpty()) {
                            nextPageUrl =
                                BASE_URL_APPENDABLE + response.body()!!._links?.next?.href
                                    ?.substringAfter("2").toString()
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