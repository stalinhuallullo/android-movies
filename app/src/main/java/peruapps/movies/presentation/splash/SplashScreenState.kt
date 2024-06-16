package peruapps.movies.presentation.splash

sealed class SplashScreenState {
    object AuthRequired : SplashScreenState()
    object AuthNotRequired : SplashScreenState()
}