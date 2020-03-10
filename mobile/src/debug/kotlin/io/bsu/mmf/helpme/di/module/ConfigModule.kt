package io.bsu.mmf.helpme.di.module

import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.domain.entity.TimePeriod
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class ConfigModule {

    @Provides
    @Named("soundId")
    fun provideSoundId() = R.raw.sound

    @Provides
    @Named("activityRecognitionInterval")
    fun provideActivityRecognitionInterval() = 5_000L

    @Provides
    @Named("timeToTellStillness")
    fun provideTimeToTellStillness() = TimePeriod(10, TimeUnit.SECONDS)

    @Provides
    @Named("timeToWaitBeforeSendingMessage")
    fun provideTimeToWaitBeforeSendingMessage() = 10L

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree = Timber.DebugTree()

   /* @Provides
    @Singleton
    fun provideCrashlyticsCore(): CrashlyticsCore = CrashlyticsCore.Builder()
            .disabled(true)
            .build()

    @Provides
    @Singleton
    fun provideCrashlytics(crashlyticsCore: CrashlyticsCore): Crashlytics = Crashlytics.Builder()
            .core(crashlyticsCore)
            .build()*/

}
