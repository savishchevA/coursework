package io.bsu.mmf.helpme.data.di.network

import android.content.Context
import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.data.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class OkHttpClientModule {

     val SOCKET_TIMEOUT = 60L

    @Singleton
    @Provides
    fun providesOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor)
                }
            }
           // .addInterceptor { manageResponse(it) }
            .connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun providesOkHttpCache(cacheFile: File): Cache {
        val cacheSize: Long = 4 * 1024 * 1024
        return Cache(cacheFile, cacheSize)
    }

    @Singleton
    @Provides
    fun providesFile(context: Context): File = context.cacheDir

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)



}