package fi.lauriari.recipe_app.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fi.lauriari.recipe_app.data.entities.FavoriteRecipe
import fi.lauriari.recipe_app.data.entities.IngredientLine
import fi.lauriari.recipe_app.data.entities.RecipeWithIngredientLines
import fi.lauriari.recipe_app.data.model.EdamamSearchResult
import fi.lauriari.recipe_app.data.model.Hits
import fi.lauriari.recipe_app.data.model.Recipe
import fi.lauriari.recipe_app.repository.RecipeRepository
import fi.lauriari.recipe_app.util.APIRequestState
import fi.lauriari.recipe_app.util.Constants.BASE_URL_APPENDABLE
import fi.lauriari.recipe_app.util.Constants.SEARCH_SCREEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    init {
        getAllFavoriteRecipes()
    }

    var selectedScreen: MutableState<String> = mutableStateOf(SEARCH_SCREEN)

    val searchNewRecipesTextState: MutableState<String> = mutableStateOf("")
    val searchFavoriteRecipesTextState: MutableState<String> = mutableStateOf("")

    val cuisineType: MutableState<String?> = mutableStateOf(null)
    val mealType: MutableState<String?> = mutableStateOf(null)
    val dishType: MutableState<String?> = mutableStateOf(null)
    private var nextPageUrl: String = ""

    var recipeList: MutableState<MutableList<Hits>> = mutableStateOf(mutableListOf())
    var buttonEnabled: MutableState<Boolean> = mutableStateOf(true)
    var visibleButtonIndex: MutableState<Int> = mutableStateOf(6)

    var selectedRecipe: Recipe? = null
    var selectedRecipeWithIngredientLines: RecipeWithIngredientLines? = null

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
                            Log.d("copytest", response.body()!!.hits.toString())
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

    private val _selectedFavoriteRecipes =
        MutableStateFlow<List<RecipeWithIngredientLines>>(emptyList())
    val selectedFavoriteRecipes: StateFlow<List<RecipeWithIngredientLines>> = _selectedFavoriteRecipes

    fun getAllFavoriteRecipes() {
        viewModelScope.launch(context = Dispatchers.IO) {
            recipeRepository.getAllFavoriteRecipes.collect { recipesWithIngredientLines ->
                _selectedFavoriteRecipes.value = recipesWithIngredientLines
            }
        }
    }

    fun searchFavoriteRecipes(filter: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            recipeRepository.getAllFavoriteRecipes.collect { recipesWithIngredientLines ->
                val filteredList = recipesWithIngredientLines.filter { singleRecipe ->
                    singleRecipe.favoriteRecipe.label.lowercase().contains(filter.lowercase())
                }
                _selectedFavoriteRecipes.value = filteredList
            }
        }
    }

    fun insertFavoriteRecipe(
        activeRecipe: FavoriteRecipe,
        ingredientLines: List<String>
    ) {

        val recipeId = selectedRecipe?.uri?.substringAfter("recipe_").toString()

        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.insertFavoriteRecipe(
                activeRecipe
            )
            ingredientLines.forEach { ingredientLine ->
                recipeRepository.insertIngredientLine(
                    IngredientLine(
                        id = 0,
                        recipeId = recipeId,
                        description = ingredientLine
                    )
                )
            }
        }
    }

    private val _isRecipeFavorited: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRecipeFavorited: StateFlow<Boolean> = _isRecipeFavorited

    fun getFavoritedRecipeStatus(favoriteId: String) {
        viewModelScope.launch {
            recipeRepository.getRecipeById(
                favoriteId
            ).collect { favoriteRecipe ->
                _isRecipeFavorited.value = favoriteRecipe != null
            }
        }
    }

    fun deleteFavoriteRecipe(favoriteId: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            recipeRepository.deleteFavoriteRecipe(favoriteId)
            recipeRepository.deleteIngredientLines(favoriteId)
        }
    }

    private val _selectedFavorite: MutableStateFlow<RecipeWithIngredientLines?> =
        MutableStateFlow(null)
    val selectedFavorite: StateFlow<RecipeWithIngredientLines?> = _selectedFavorite

    fun getSelectedFavorite(favoriteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.getSelectedFavorite(favoriteId).collect { favorite ->
                _selectedFavorite.value = favorite
            }
        }
    }


}