package peruapps.movies.presentation.navigator

import android.app.Activity
import android.view.View
import peruapps.movies.presentation.movie.MovieModel

interface Navigator {

    fun goToAuth()

    fun goToList()

    fun goToDetail(activity: Activity, view: View, movie: MovieModel)
}