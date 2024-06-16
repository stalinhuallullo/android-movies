package peruapps.movies.presentation.auth

sealed class AuthScreenState {
    object Loading : AuthScreenState()
    object NotLoading : AuthScreenState()
    object Success : AuthScreenState()
    class Failure(val message: String?) : AuthScreenState()
}