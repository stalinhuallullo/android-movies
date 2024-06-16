package peruapps.movies.presentation.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import peruapps.movies.R
import peruapps.movies.databinding.ActivityListMovieBinding
import peruapps.movies.presentation.dialog.MessageDialog
import peruapps.movies.presentation.dialog.OptionDialog
import peruapps.movies.presentation.navigator.Navigator
import peruapps.movies.presentation.util.onEndless

class ListMovieActivity : AppCompatActivity(), ListMoviesAdapter.OnClickItemListener {

    private val viewModel: ListMovieViewModel by inject()

    private val binding: ActivityListMovieBinding by inject {
        parametersOf(
            this,
            R.layout.activity_list_movie
        )
    }

    private val navigator: Navigator by inject()

    private val adapter: ListMoviesAdapter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setViewModel()
        setRecycler()
        setOnClick()
    }

    private fun setBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setRecycler() {
        binding.moviesRecycler.onFlingListener = null
        PagerSnapHelper().attachToRecyclerView(binding.moviesRecycler)
        binding.moviesRecycler.adapter = adapter
        binding.moviesRecycler.onEndless {
            adapter.showShimmerPaginate()
            viewModel.getMovies()
        }
        adapter.showShimmerData()
    }

    private fun setViewModel() {
        viewModel.apply {
            movieLiveData.observe(::getLifecycle, ::observerMovies)
            logoutLiveData.observe(::getLifecycle, ::observerLogout)
            errorLiveData.observe(::getLifecycle, ::observerError)
            getMovies()
        }
    }

    private fun setOnClick() {
        binding.logoutButton.setOnClickListener {
            OptionDialog.Builder(this)
                .setMessage(getString(R.string.logout_message))
                .setTitle(getString(R.string.logout_title))
                .setOnClickAccept {
                    it.dismiss()
                    viewModel.logout()
                }
                .setOnClickCancel { it.dismiss() }
                .show()
        }
    }

    override fun onClickItem(view: View, movie: MovieModel) {
        navigator.goToDetail(this, view, movie)
    }

    /** Observers **/

    private fun observerMovies(movies: MutableList<MovieModel>) {
        adapter.addMovies(movies)
    }

    private fun observerLogout(any: Any) {
        navigator.goToAuth()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun observerError(message: String) {
        MessageDialog.Builder(this)
            .setMessage(message)
            .setTitle(getString(R.string.error))
            .setPositiveButtonText(getString(R.string.retry))
            .setOnClickAccept {
                viewModel.getMovies()
                it.dismiss()
            }
            .show()
    }


    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, ListMovieActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }
    }
}