package peruapps.movies.data.auth

import com.google.gson.annotations.SerializedName

class AuthResponse(
    @SerializedName("token")
    val token: String
)