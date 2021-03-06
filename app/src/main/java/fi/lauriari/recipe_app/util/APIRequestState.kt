package fi.lauriari.recipe_app.util

sealed class APIRequestState<out T> {
    object Idle : APIRequestState<Nothing>()
    object Loading : APIRequestState<Nothing>()
    object EmptyList : APIRequestState<Nothing>()
    object BadResponse : APIRequestState<Nothing>()
    data class Success<T>(val responseValue: T) : APIRequestState<T>()
    data class Error(val error: Throwable) : APIRequestState<Nothing>()
}
