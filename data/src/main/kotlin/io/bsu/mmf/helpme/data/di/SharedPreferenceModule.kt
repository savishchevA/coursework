package io.bsu.mmf.helpme.data.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
}
