package peruapps.movies.presentation.auth

sealed class AuthState {
    object ErrorDisabled : AuthState()
    object EmailInvalid : AuthState()
    object PasswordInvalid : AuthState()
}