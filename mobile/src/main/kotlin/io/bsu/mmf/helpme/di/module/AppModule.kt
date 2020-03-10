package io.bsu.mmf.helpme.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.SosApplication

@Module
class AppModule {
    @Provides
    fun provideContext(app: SosApplication): Context = app.applicationContext
}
