package peruapps.movies.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import peruapps.movies.R
import peruapps.movies.databinding.ActivityDetailMovieMovieBinding
import peruapps.movies.presentation.movie.MovieModel

class DetailMovieActivity : AppCompatActivity() {

    private val viewModel: DetailMovieViewModel by inject()

    private val binding: ActivityDetailMovieMovieBinding by inject {
        parametersOf(this, R.layout.activity_detail_movie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        getExtra()
        setOnClick()
    }

    private fun setBinding() {
        binding.viewModel = viewModel
    }

    private fun getExtra() {
        viewModel.movie = intent.extras?.getParcelable(MOVIE_KEY)
    }

    private fun setOnClick() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie_key"

        fun getCallingIntent(context: Context, movie: MovieModel): Intent {
            return Intent(context, DetailMovieActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(MOVIE_KEY, movie)
            }
        }
    }
}