package peruapps.movies.data.api

import peruapps.movies.data.auth.AuthBody
import peruapps.movies.data.auth.AuthResponse
import peruapps.movies.data.base.BaseResponse
import peruapps.movies.data.movie.MovieEntityResponse
import retrofit2.Response
import retrofit2.http.*

interface RestApi {

    @POST("v1/auth/login")
    suspend fun auth(@Body authBody: AuthBody): Response<BaseResponse<AuthResponse>>

    @GET("v1/movies?")
    suspend fun getMovies(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<BaseResponse<MutableList<MovieEntityResponse>>>
}