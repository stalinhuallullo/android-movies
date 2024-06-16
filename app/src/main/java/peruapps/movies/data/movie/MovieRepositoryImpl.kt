package peruapps.movies.data.movie

import peruapps.movies.data.api.RestApi
import peruapps.movies.data.util.getExceptionByCode
import peruapps.movies.domain.movie.Movie
import peruapps.movies.domain.movie.MovieRepository

class MovieRepositoryImpl(private val restApi: RestApi, private val movieMapper: MovieMapper) :
    MovieRepository {
    override suspend fun getMovies(token: String, page: Int): MutableList<Movie> {
        val response = restApi.getMovies(token, page)

        if (response.isSuccessful) {
            return movieMapper.parse(response.body()?.data)
        } else {
            throw getExceptionByCode(response.code())
        }
    }
}