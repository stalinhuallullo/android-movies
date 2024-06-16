package peruapps.movies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import peruapps.movies.data.di.dataModule
import peruapps.movies.domain.di.domainModule
import peruapps.movies.presentation.auth.di.authModule
import peruapps.movies.presentation.detail.di.detailMovieModule
import peruapps.movies.presentation.movie.di.listMovieModule
import peruapps.movies.presentation.navigator.di.navigatorModule
import peruapps.movies.presentation.splash.di.splashModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(dataModule, domainModule) + getPresentationModules())
        }
    }

    private fun getPresentationModules() =
        listOf(navigatorModule, splashModule, authModule, listMovieModule, detailMovieModule)
}