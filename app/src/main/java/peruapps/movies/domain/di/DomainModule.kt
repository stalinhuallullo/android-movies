package peruapps.movies.domain.di

import org.koin.dsl.module
import peruapps.movies.data.auth.AuthRepositoryImpl
import peruapps.movies.data.movie.MovieRepositoryImpl
import peruapps.movies.data.session.SessionRepositoryImpl
import peruapps.movies.domain.auth.AuthInteractor
import peruapps.movies.domain.auth.AuthRepository
import peruapps.movies.domain.auth.AuthUseCase
import peruapps.movies.domain.movie.GetMovieInteractor
import peruapps.movies.domain.movie.GetMovieUseCase
import peruapps.movies.domain.movie.MovieModelMapper
import peruapps.movies.domain.movie.MovieRepository
import peruapps.movies.domain.session.SessionRepository

val domainModule = module {
    factory<SessionRepository> {
        SessionRepositoryImpl(
            get()
        )
    }

    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AuthUseCase> { AuthInteractor(get(), get()) }

    factory<MovieRepository> {
        MovieRepositoryImpl(
            get(),
            get()
        )
    }
    factory<GetMovieUseCase> { GetMovieInteractor(get(), get()) }
    factory { MovieModelMapper() }
}