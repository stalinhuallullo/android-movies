package peruapps.movies.data.di

import android.content.Context
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import peruapps.movies.BuildConfig
import peruapps.movies.data.movie.MovieMapper
import peruapps.movies.data.api.RestApi
import peruapps.movies.data.api.RetrofitConfig
import peruapps.movies.data.preference.SharedPreferenceHelper
import peruapps.movies.data.preference.SharedPreferenceHelperImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(RetrofitConfig.okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build().create(RestApi::class.java)
    }

    single<SharedPreferenceHelper> {
        SharedPreferenceHelperImpl(
            androidContext().getSharedPreferences(
                SharedPreferenceHelperImpl.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        )
    }

    factory { MovieMapper() }
}