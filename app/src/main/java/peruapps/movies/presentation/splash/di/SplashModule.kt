package peruapps.movies.presentation.splash.di

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import peruapps.movies.databinding.ActivitySplashBinding
import peruapps.movies.presentation.splash.SplashViewModel

val splashModule = module {
    viewModel { SplashViewModel(get()) }

    factory<ActivitySplashBinding> { (activity: Activity, @LayoutRes layout: Int) ->
        DataBindingUtil.setContentView(
            activity,
            layout
        )
    }
}