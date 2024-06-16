package peruapps.movies.data.preference

import android.content.SharedPreferences
import peruapps.movies.BuildConfig

class SharedPreferenceHelperImpl(
    private val sharedPreferences: SharedPreferences
) : SharedPreferenceHelper {

    override var isLogin: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGIN, false)
        set(value) {
            sharedPreferences.edit().putBoolean(IS_LOGIN, value).apply()
        }

    override var token: String
        get() = "$BEARER ${sharedPreferences.getString(TOKEN, "")}"
        set(value) {
            sharedPreferences.edit().putString(TOKEN, value).apply()
        }

    companion object {
        const val PREFERENCE_NAME = BuildConfig.APPLICATION_ID
        private const val BEARER = "Bearer"
        private const val IS_LOGIN = "is_login"
        private const val TOKEN = "token"
    }
}