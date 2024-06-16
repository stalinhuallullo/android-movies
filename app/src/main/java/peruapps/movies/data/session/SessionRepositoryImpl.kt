package peruapps.movies.data.session

import peruapps.movies.data.preference.SharedPreferenceHelper
import peruapps.movies.domain.session.SessionRepository

class SessionRepositoryImpl(private val sharedPreferencesHelper: SharedPreferenceHelper) :
    SessionRepository {

    override fun isLogin() = sharedPreferencesHelper.isLogin

    override fun saveIsLogin(isLogin: Boolean) {
        sharedPreferencesHelper.isLogin = isLogin
    }

    override fun getToken() = sharedPreferencesHelper.token

    override fun saveToken(token: String?) {
        sharedPreferencesHelper.token = token ?: ""
    }
}