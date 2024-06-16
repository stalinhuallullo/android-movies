package peruapps.movies.presentation.auth.di

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import peruapps.movies.databinding.ActivityAuthBinding
import peruapps.movies.presentation.auth.AuthViewModel

val authModule = module {

    viewModel { AuthViewModel(get()) }

    factory<ActivityAuthBinding> { (activity: Activity, @LayoutRes layout: Int) ->
        DataBindingUtil.setContentView(
            activity,
            layout
        )
    }
}