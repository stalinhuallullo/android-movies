package peruapps.movies.data.movie

import com.google.gson.annotations.SerializedName

class MovieDetailEntityResponse (
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("img") val image: String
)