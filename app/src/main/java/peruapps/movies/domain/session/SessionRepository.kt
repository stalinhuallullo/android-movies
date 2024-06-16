package peruapps.movies.domain.session

interface SessionRepository {
    fun isLogin() : Boolean
    fun saveIsLogin(isLogin : Boolean)
    fun getToken() : String
    fun saveToken(token : String?)
}