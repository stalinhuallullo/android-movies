package peruapps.movies.domain.movie

import peruapps.movies.presentation.movie.MovieDetailModel
import peruapps.movies.presentation.movie.MovieModel

class MovieModelMapper {

    fun parse(list: MutableList<Movie>): MutableList<MovieModel> {
        val parseList = mutableListOf<MovieModel>()
        list.forEach {
            val detail = MovieDetailModel(
                it.detail.title,
                it.detail.description,
                it.detail.image
            )
            val movie = MovieModel(it.id, detail)
            parseList.add(movie)
        }
        return parseList
    }
}