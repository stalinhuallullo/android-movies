package peruapps.movies.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import peruapps.movies.BuildConfig
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    private const val TIMEOUT_READ = 40L
    private const val TIMEOUT_WRITE = 40L
    private const val TIMEOUT_CONNECT = 30L

    val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
        .addInterceptor(getInterceptor())
        .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
        .build()

    private fun getInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }
}