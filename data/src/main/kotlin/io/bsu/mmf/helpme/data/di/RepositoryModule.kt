package io.bsu.mmf.helpme.data.di

import dagger.Binds
import dagger.Module
import io.bsu.mmf.helpme.data.repository.FirebaseContactRepository
import io.bsu.mmf.helpme.data.repository.FirebaseContactRepositoryImpl
import io.bsu.mmf.helpme.data.repository.auth.AuthRepository
import io.bsu.mmf.helpme.data.repository.auth.AuthRepositoryImpl
import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository
import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepositoryImpl
import io.bsu.mmf.helpme.data.repository.weather.WeatherRepository
import io.bsu.mmf.helpme.data.repository.weather.WeatherRepositoryImpl
import io.bsu.mmf.helpme.data.sensors.ContactsRepository
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindContactsRepository(source: ContactsRepository): ContactsLocalRepository

    @Binds
    abstract fun bindAuthRepository(source: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindFirebaseContactRepository(source: FirebaseContactRepositoryImpl): FirebaseContactRepository

    @Binds
    abstract fun bindWeatherRepository(source: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindSharedPreferenceRepository(source: SharedPreferenceRepositoryImpl): SharedPreferenceRepository
}