package peruapps.movies.presentation.movie.di

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import peruapps.movies.databinding.ActivityListMovieBinding
import peruapps.movies.presentation.movie.ListMovieViewModel
import peruapps.movies.presentation.movie.ListMoviesAdapter

val listMovieModule = module {
    viewModel { ListMovieViewModel(get(), get(), get()) }

    factory<ActivityListMovieBinding> { (activity: Activity, @LayoutRes layout: Int) ->
        DataBindingUtil.setContentView(
            activity,
            layout
        )
    }

    factory { (onClickItemListener: ListMoviesAdapter.OnClickItemListener) ->
        ListMoviesAdapter(
            onClickItemListener
        )
    }
}