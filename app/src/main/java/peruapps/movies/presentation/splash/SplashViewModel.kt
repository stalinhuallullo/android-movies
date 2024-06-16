package peruapps.movies.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import peruapps.movies.domain.auth.AuthUseCase

class SplashViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _isAuthLiveData = MutableLiveData<SplashScreenState>()

    val isAuthLiveData: LiveData<SplashScreenState>
        get() = _isAuthLiveData

    fun checkUserAuth() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            if (authUseCase.isAuth()) {
                launch(Dispatchers.Main) {
                    _isAuthLiveData.value = SplashScreenState.AuthNotRequired
                }
            } else {
                launch(Dispatchers.Main) {
                    _isAuthLiveData.value = SplashScreenState.AuthRequired
                }
            }
        }
    }
}