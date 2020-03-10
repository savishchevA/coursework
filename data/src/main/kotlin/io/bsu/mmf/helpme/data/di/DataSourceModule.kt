package io.bsu.mmf.helpme.data.di

import dagger.Binds
import dagger.Module
import io.bsu.mmf.helpme.data.dataSource.*
import io.bsu.mmf.helpme.data.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.data.dataSource.sharedPreference.SharedPreferenceDataSource
import io.bsu.mmf.helpme.data.dataSource.sharedPreference.SharedPreferenceDataSourceImpl
import io.bsu.mmf.helpme.data.dataSource.weather.WeatherDataSource
import io.bsu.mmf.helpme.data.dataSource.weather.WeatherDataSourceImpl


@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindContactsDataSource(source: ContactsDataSourceImpl): ContactsDataSource

    @Binds
    abstract fun bindAuthDataSource(source: AuthDataSourceImpl): AuthDataSource

    @Binds
    abstract fun bindFireBaseContactsDataSource(source: FireBaseContactsDataSourceImpl): FireBaseContactsDataSource

    @Binds
    abstract fun bindWeatherDataSource(source: WeatherDataSourceImpl): WeatherDataSource

    @Binds
    abstract fun bindSharedPreferenceDataSource(source: SharedPreferenceDataSourceImpl): SharedPreferenceDataSource
}