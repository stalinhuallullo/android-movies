package peruapps.movies.presentation.navigator

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import peruapps.movies.R
import peruapps.movies.presentation.auth.AuthActivity
import peruapps.movies.presentation.detail.DetailMovieActivity
import peruapps.movies.presentation.movie.ListMovieActivity
import peruapps.movies.presentation.movie.MovieModel

class NavigatorImpl(private val context: Context) : Navigator {

    override fun goToAuth() {
        context.startActivity(AuthActivity.getCallingIntent(context))
    }

    override fun goToList() {
        context.startActivity(ListMovieActivity.getCallingIntent(context))
    }

    override fun goToDetail(activity: Activity, view: View, movie: MovieModel) {
        val intent = DetailMovieActivity.getCallingIntent(context, movie)
        val options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                view,
                context.getString(R.string.movie_transition)
            )
        activity.startActivity(intent, options.toBundle())
    }
}