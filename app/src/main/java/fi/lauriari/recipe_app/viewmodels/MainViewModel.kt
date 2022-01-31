package fi.lauriari.recipe_app.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    val searchTextState: MutableState<String> = mutableStateOf("")

}