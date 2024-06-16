package peruapps.movies.presentation.navigator.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import peruapps.movies.presentation.navigator.Navigator
import peruapps.movies.presentation.navigator.NavigatorImpl

val navigatorModule = module {
    single<Navigator> { NavigatorImpl(androidContext()) }
}