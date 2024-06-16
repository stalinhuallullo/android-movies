package peruapps.movies.domain.movie

interface MovieRepository {
    suspend fun getMovies(token: String, page: Int): MutableList<Movie>
}