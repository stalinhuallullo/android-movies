package peruapps.movies.domain.auth

import peruapps.movies.data.auth.AuthBody
import peruapps.movies.data.auth.AuthResponse

interface AuthRepository {
    suspend fun auth(authBody: AuthBody) : AuthResponse?
}