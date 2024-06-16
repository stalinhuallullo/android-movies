package peruapps.movies.data.auth

import peruapps.movies.data.api.RestApi
import peruapps.movies.data.util.getExceptionByCode
import peruapps.movies.domain.auth.AuthRepository

class AuthRepositoryImpl(private val restApi: RestApi) :
    AuthRepository {

    override suspend fun auth(authBody: AuthBody): AuthResponse? {
        val response = restApi.auth(authBody)
        if (response.isSuccessful) {
            return response.body()?.data
        } else {
            throw getExceptionByCode(response.code())
        }
    }
}