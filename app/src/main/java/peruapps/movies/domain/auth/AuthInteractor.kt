package peruapps.movies.domain.auth

import peruapps.movies.data.auth.AuthBody
import peruapps.movies.domain.session.SessionRepository

class AuthInteractor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) : AuthUseCase {

    override suspend fun auth(auth: Auth) {
        val authResponse = authRepository.auth(
            AuthBody(
                auth.email,
                auth.password
            )
        )
        sessionRepository.saveIsLogin(true)
        sessionRepository.saveToken(authResponse?.token)
    }

    override suspend fun isAuth(): Boolean {
        return sessionRepository.isLogin()
    }

    override suspend fun logout() {
        sessionRepository.saveIsLogin(false)
        sessionRepository.saveToken(null)
    }
}