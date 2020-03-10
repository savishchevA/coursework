package io.bsu.mmf.helpme.data.di.network

import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.data.network.WeatherApi
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module(includes = [RetrofitModule::class])
class ServiceModule {

    @Singleton
    @Provides
    fun provideWeatherApi(
            retrofit: Retrofit
    ): WeatherApi = retrofit.create()

}