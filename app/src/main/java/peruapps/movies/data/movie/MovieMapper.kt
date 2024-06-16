package peruapps.movies.data.movie

import peruapps.movies.domain.movie.Movie
import peruapps.movies.domain.movie.MovieDetail

class MovieMapper {

    fun parse(list: MutableList<MovieEntityResponse>?): MutableList<Movie> {
        val parseList = mutableListOf<Movie>()
        list?.forEach {
            val detail = MovieDetail(it.detail.title, it.detail.description, it.detail.image)
            val movie = Movie(it.id, detail)
            parseList.add(movie)
        }
        return parseList
    }
}