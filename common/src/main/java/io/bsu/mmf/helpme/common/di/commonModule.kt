package io.bsu.mmf.helpme.common.di

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.preference.PreferenceManager
import android.telephony.SmsManager
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import io.bsu.mmf.helpme.common.BuildConfig
import io.bsu.mmf.helpme.common.dataSource.*
import io.bsu.mmf.helpme.common.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.common.dataSource.base.BaseRemoteDataSource
import io.bsu.mmf.helpme.common.dataSource.profile.ProfileDataSource
import io.bsu.mmf.helpme.common.dataSource.profile.ProfileDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.sharedPreference.SharedPreferenceDataSource
import io.bsu.mmf.helpme.common.dataSource.sharedPreference.SharedPreferenceDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSource
import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.train.TrainDataSource
import io.bsu.mmf.helpme.common.dataSource.train.TrainDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.weather.WeatherDataSource
import io.bsu.mmf.helpme.common.dataSource.weather.WeatherDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.AppDatabase
import io.bsu.mmf.helpme.common.mappers.auth.AuthResponseToDtoMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactFBToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactToFBMapper
import io.bsu.mmf.helpme.common.mappers.profile.ProfileDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.profile.ProfileRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.train.TrainDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.train.TrainRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.weather.CurrentWeatherToDtoMapper
import io.bsu.mmf.helpme.common.network.WeatherApi
import io.bsu.mmf.helpme.common.repository.*
import io.bsu.mmf.helpme.common.repository.auth.AuthRepository
import io.bsu.mmf.helpme.common.repository.auth.AuthRepositoryImpl
import io.bsu.mmf.helpme.common.repository.geocoding.GeocodeRepository
import io.bsu.mmf.helpme.common.repository.geocoding.GeocodeRepositoryImpl
import io.bsu.mmf.helpme.common.repository.profile.ProfileRepository
import io.bsu.mmf.helpme.common.repository.profile.ProfileRepositoryImpl
import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository
import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepositoryImpl
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepository
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepositoryImpl
import io.bsu.mmf.helpme.common.repository.train.TrainRepository
import io.bsu.mmf.helpme.common.repository.train.TrainRepositoryImpl
import io.bsu.mmf.helpme.common.repository.weather.WeatherRepository
import io.bsu.mmf.helpme.common.repository.weather.WeatherRepositoryImpl
import io.bsu.mmf.helpme.common.sensors.ContactsRepository
import io.bsu.mmf.helpme.common.usecase.GetFBContactUseCase
import io.bsu.mmf.helpme.common.usecase.SaveFBContactUseCase
import io.bsu.mmf.helpme.common.usecase.auth.CheckUserLoginUseCase
import io.bsu.mmf.helpme.common.usecase.auth.CreateAccountUseCase
import io.bsu.mmf.helpme.common.usecase.auth.LoginWithEmailUseCase
import io.bsu.mmf.helpme.common.usecase.auth.ResetPasswordUseCase
import io.bsu.mmf.helpme.common.usecase.contact.*
import io.bsu.mmf.helpme.common.usecase.location.GetCoordinatesFromAddressUseCase
import io.bsu.mmf.helpme.common.usecase.profile.*
import io.bsu.mmf.helpme.common.usecase.sharedPreference.*
import io.bsu.mmf.helpme.common.usecase.sharedPreference.time.*
import io.bsu.mmf.helpme.common.usecase.sms.SendMessageForAllUseCase
import io.bsu.mmf.helpme.common.usecase.sms.SendSmsUseCase
import io.bsu.mmf.helpme.common.usecase.train.*
import io.bsu.mmf.helpme.common.usecase.weather.GetCurrentWeatherUseCase
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

val commonModule = module {

    //android
    // single { androidApplication().applicationContext }
    single { createSharedPreference(get()) }

    //dataSources
    single<AuthDataSource> {
        AuthDataSourceImpl(get())
    }

    single<ContactsDataSource> {
        ContactsDataSourceImpl(get(), get(), get())
    }
    single<FireBaseContactsDataSource> { FireBaseContactsDataSourceImpl(get(), get()) }
    single<SharedPreferenceDataSource> { SharedPreferenceDataSourceImpl(get()) }
    single<WeatherDataSource> { WeatherDataSourceImpl(get(), get(), get()) }
    single<ProfileDataSource> { ProfileDataSourceImpl(get(), get(), get()) }
    single<TrainDataSource> { TrainDataSourceImpl(get(), get(), get()) }
    single { BaseRemoteDataSource() }

    factory<SendSmsDataSource> { SendSmsDataSourceImpl(get()) }


    //network
    single { createOkHttpClient(get(), get()) }
    single { createOkHttpCache(get()) }
    single { createFile(get()) }
    single { createMoshi() }
    single { createMoshiConverter(get()) }
    single { createHttpLoggingInterceptor() }
    single { createRetrofit(get(), get(), get()) }
    single { createUrl() }
    single { createWeatherApi(get()) }

    //dataBase
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "sosapp.db").build() }
    single { get<AppDatabase>().contactsDao() }
    single { get<AppDatabase>().profileDao() }
    single { get<AppDatabase>().trainDao() }

    //mappers
    single { AuthResponseToDtoMapper() }
    single { ContactDtoToRoomItemMapper() }
    single { ContactRoomItemToDtoMapper() }
    single { ContactFBToDtoMapper() }
    single { ContactToFBMapper() }
    single { CurrentWeatherToDtoMapper() }
    single { ProfileRoomItemToDtoMapper() }
    single { ProfileDtoToRoomItemMapper() }
    single { TrainDtoToRoomItemMapper() }
    single { TrainRoomItemToDtoMapper() }

    //repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<SharedPreferenceRepository> { SharedPreferenceRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<FirebaseContactRepository> { FirebaseContactRepositoryImpl(get()) }
    single<ContactsLocalRepository> { ContactsRepository(get()) }
    single<TrainRepository> { TrainRepositoryImpl(get()) }
    single<GeocodeRepository> { GeocodeRepositoryImpl(get()) }
    factory<SendSmsRepository> { SendSmsRepositoryImpl(get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(get()) }

    //useCases


    //auth
    single { CheckUserLoginUseCase(get()) }
    single { CreateAccountUseCase(get()) }
    single { LoginWithEmailUseCase(get()) }
    single { ResetPasswordUseCase(get()) }

    //sharedPreference
    single { GetRegistrationStatusUseCase(get()) }
    single { SetRegistrationStatusUseCase(get()) }
    single { IsSendAllContactsUseCase(get()) }
    single { SetSendAllContactsUseCase(get()) }
    single { SetAlarmTimeUseCase(get()) }
    single { GetAlarmTimeUseCase(get()) }
    single { SetStayTimeUseCase(get()) }
    single { GetStayTimeUseCase(get()) }

    //weather
    single { GetCurrentWeatherUseCase(get()) }

    //firebase
    single { SaveFBContactUseCase(get()) }
    single { GetFBContactUseCase(get()) }

    //contatcs
    factory { GetAllContactUseCase(get()) }
    single { SaveContactUseCase(get()) }
    single { GetAllSourceUseCase(get()) }
    single { DeleteContactUseCase(get()) }
    single { GetContactByIdUseCase(get()) }
    single { UpdateContactUseCase(get()) }
    single { UpdatePrimaryContactUseCase(get()) }
    single { GetObservablePrimaryContactUseCase(get()) }
    single { GetContactsCountUseCase(get()) }

    //geocoder
    single { GetCoordinatesFromAddressUseCase(get()) }

    //sms
    factory { SendSmsUseCase(get(), get()) }
    factory { SendMessageForAllUseCase(get(), get(), get()) }


    //profile
    single { GetProfileUseCase(get()) }
    single { UpdateTrainTimeUseCase(get()) }
    single { UpdateTrainDistanceUseCase(get()) }
    single { CreateProfileUseCase(get()) }
    single { UpdateCommonMessageUseCase(get()) }
    single { UpdateStayTimeUseCase(get()) }
    single { UpdateAlarmTimeUseCase(get()) }

    //train
    single { GetTrainsUseCase(get()) }
    single { SaveTrainUseCase(get()) }
    single { DeleteTrainUseCase(get()) }

    //geocoder
    single { createGeocoder(get()) }

    //sms manager
    single { createAndroidSmsManager() }


}
const val SOCKET_TIMEOUT = 60L

fun createSharedPreference(context: Context): SharedPreferences =
    PreferenceManager.getDefaultSharedPreferences(context)


fun createOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }
        .connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

fun createOkHttpCache(file: File): Cache {
    val cacheSize: Long = 4 * 1024 * 1024
    return Cache(file, cacheSize)
}

fun createFile(context: Context): File = context.filesDir

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

fun createRetrofit(
    url: String,
    okHttpClient: OkHttpClient,
    moshiConverter: MoshiConverterFactory
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .addConverterFactory(moshiConverter)
        .baseUrl(url)
        .addCallAdapterFactory(ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
        .client(okHttpClient)
        .build()
}


fun createMoshiConverter(moshi: Moshi) = MoshiConverterFactory.create(moshi)

fun createMoshi() = Moshi.Builder().build()

fun createUrl() = "http://api.openweathermap.org/data/2.5/"

fun createWeatherApi(
    retrofit: Retrofit
): WeatherApi = retrofit.create()

fun createGeocoder(context: Context) = Geocoder(context, Locale.ENGLISH)

fun createAndroidSmsManager() = SmsManager.getDefault()