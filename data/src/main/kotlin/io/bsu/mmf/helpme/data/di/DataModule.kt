package io.bsu.mmf.helpme.data.di

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.google.android.gms.location.DetectedActivity
import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.data.BuildConfig
import io.bsu.mmf.helpme.data.database.KeyValueService
import io.bsu.mmf.helpme.data.database.PersistentRepository
import io.bsu.mmf.helpme.data.database.service.PreferencesService
import io.bsu.mmf.helpme.data.device.sms.SmsManager
import io.bsu.mmf.helpme.data.device.sms.SmsService
import io.bsu.mmf.helpme.data.device.sms.service.AndroidSmsService
import io.bsu.mmf.helpme.data.device.sound.SoundManager
import io.bsu.mmf.helpme.data.device.sound.SoundService
import io.bsu.mmf.helpme.data.device.sound.service.AndroidSoundService
import io.bsu.mmf.helpme.data.sensors.ActivityDetectionService
import io.bsu.mmf.helpme.data.sensors.LocationService
import io.bsu.mmf.helpme.data.sensors.SensorsRepository
import io.bsu.mmf.helpme.data.sensors.service.PlayServicesActivityDetectionService
import io.bsu.mmf.helpme.data.sensors.service.PlayServicesLocationService
import io.bsu.mmf.helpme.domain.driver.SmsDriver
import io.bsu.mmf.helpme.domain.driver.SoundDriver
import io.bsu.mmf.helpme.domain.entity.TimePeriod
import io.bsu.mmf.helpme.domain.repository.LocalRepository
import io.bsu.mmf.helpme.domain.repository.LocationRepository
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.activity.config.ActivityParams
import io.nlopez.smartlocation.location.config.LocationAccuracy
import io.nlopez.smartlocation.location.config.LocationParams
import io.nlopez.smartlocation.rx.ObservableFactory
import io.reactivex.Observable
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideKeyValueService(preferences: SharedPreferences): KeyValueService = PreferencesService(preferences)

    @Provides
    @Singleton
    internal fun provideLocalRepository(keyValueService: KeyValueService): LocalRepository = PersistentRepository(keyValueService)



    @Provides
    @Singleton
    internal fun provideSmartLocation(context: Context) = SmartLocation.with(context)

    @Provides
    @Singleton
    internal fun provideActivityRecognitionConfig(@Named("activityRecognitionInterval") activityRecognitionInterval: Long) =
            ActivityParams.Builder().setInterval(activityRecognitionInterval).build()

    @Provides
    @Singleton
    internal fun provideActivityRecognitionControll(smartLocation: SmartLocation, activityParams: ActivityParams) = smartLocation.activity().config(activityParams)

    @Provides
    @Singleton
    internal fun provideActivityRecognitionObservable(activityRecognitionControl: SmartLocation.ActivityRecognitionControl) = ObservableFactory.from(activityRecognitionControl)

    @Provides
    @Singleton
    internal fun provideActivityDetectionService(observable: Observable<DetectedActivity>): ActivityDetectionService = PlayServicesActivityDetectionService(observable)

    @Provides
    @Singleton
    internal fun provideLocationConfig() = LocationParams.Builder().setAccuracy(LocationAccuracy.HIGH).build()

    @Provides
    @Singleton
    internal fun provideLocationControl(smartLocation: SmartLocation, locationParams: LocationParams) = smartLocation.location().config(locationParams).get().oneFix()

    @Provides
    @Singleton
    internal fun provideLocationObservable(locationControl: SmartLocation.LocationControl) = ObservableFactory.from(locationControl)

    @Provides
    @Singleton
    internal fun provideLocationService(observable: Observable<Location>) : LocationService = PlayServicesLocationService(observable)

    @Provides
    @Singleton
    internal fun provideLocationRepository(activityDetectionService: ActivityDetectionService, @Named("timeToTellStillness") timeToTellStillness: TimePeriod,
                                           locationService: LocationService, localRepository: LocalRepository): LocationRepository =
            SensorsRepository(activityDetectionService, timeToTellStillness,  locationService, localRepository)


    @Provides
    @Singleton
    internal fun provideSoundService(context: Context): SoundService = AndroidSoundService(context)

    @Provides
    @Singleton
    internal fun provideSoundManager(soundService: SoundService, @Named("soundId") soundId: Int): SoundDriver = SoundManager(soundService, soundId)

    @Provides
    @Singleton
    internal fun provideAndroidSmsManager() = android.telephony.SmsManager.getDefault()

    @Provides
    @Singleton
    internal fun provideSmsService(smsManager: android.telephony.SmsManager): SmsService = AndroidSmsService(smsManager)

    @Provides
    @Singleton
    internal fun provideSmsDriver(smsService: SmsService): SmsDriver = SmsManager(smsService)
}
