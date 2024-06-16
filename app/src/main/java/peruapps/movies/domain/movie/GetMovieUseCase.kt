package peruapps.movies.domain.movie

interface GetMovieUseCase {
    suspend fun getMovies(page : Int): MutableList<Movie>
}