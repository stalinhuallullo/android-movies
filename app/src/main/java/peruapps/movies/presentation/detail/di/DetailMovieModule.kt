package peruapps.movies.presentation.detail.di

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import peruapps.movies.databinding.ActivityDetailMovieMovieBinding
import peruapps.movies.presentation.detail.DetailMovieViewModel

val detailMovieModule = module {
    viewModel { DetailMovieViewModel() }

    factory<ActivityDetailMovieMovieBinding> { (activity: Activity, @LayoutRes layout: Int) ->
        DataBindingUtil.setContentView(
            activity,
            layout
        )
    }
}