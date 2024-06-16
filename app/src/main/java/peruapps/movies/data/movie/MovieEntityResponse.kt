package peruapps.movies.data.movie

import com.google.gson.annotations.SerializedName

class MovieEntityResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("detail") val detail: MovieDetailEntityResponse
)