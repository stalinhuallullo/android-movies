package peruapps.movies.domain.auth

interface AuthUseCase {
    suspend fun auth(auth: Auth)

    suspend fun isAuth() : Boolean

    suspend fun logout()
}