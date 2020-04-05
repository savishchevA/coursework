package io.bsu.mmf.helpme.common.di

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import android.preference.PreferenceManager
import android.telephony.SmsManager
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.bsu.mmf.helpme.common.BuildConfig
import io.bsu.mmf.helpme.common.dataSource.*
import io.bsu.mmf.helpme.common.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.common.dataSource.base.BaseRemoteDataSource
import io.bsu.mmf.helpme.common.dataSource.sharedPreference.SharedPreferenceDataSource
import io.bsu.mmf.helpme.common.dataSource.sharedPreference.SharedPreferenceDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSource
import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSourceImpl
import io.bsu.mmf.helpme.common.dataSource.weather.WeatherDataSource
import io.bsu.mmf.helpme.common.dataSource.weather.WeatherDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.AppDatabase
import io.bsu.mmf.helpme.common.mappers.auth.AuthResponseToDtoMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactFBToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactToFBMapper
import io.bsu.mmf.helpme.common.mappers.weather.CurrentWeatherToDtoMapper
import io.bsu.mmf.helpme.common.network.WeatherApi
import io.bsu.mmf.helpme.common.repository.*
import io.bsu.mmf.helpme.common.repository.auth.AuthRepository
import io.bsu.mmf.helpme.common.repository.auth.AuthRepositoryImpl
import io.bsu.mmf.helpme.common.repository.geocoding.GeocodeRepository
import io.bsu.mmf.helpme.common.repository.geocoding.GeocodeRepositoryImpl
import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository
import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepositoryImpl
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepository
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepositoryImpl
import io.bsu.mmf.helpme.common.repository.weather.WeatherRepository
import io.bsu.mmf.helpme.common.repository.weather.WeatherRepositoryImpl
import io.bsu.mmf.helpme.common.sensors.ContactsRepository
import io.bsu.mmf.helpme.common.usecase.SaveFBContactUseCase
import io.bsu.mmf.helpme.common.usecase.auth.CheckUserLoginUseCase
import io.bsu.mmf.helpme.common.usecase.auth.CreateAccountUseCase
import io.bsu.mmf.helpme.common.usecase.auth.LoginWithEmailUseCase
import io.bsu.mmf.helpme.common.usecase.auth.ResetPasswordUseCase
import io.bsu.mmf.helpme.common.usecase.contact.*
import io.bsu.mmf.helpme.common.usecase.location.GetCoordinatesFromAddressUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.GetRegistrationStatusUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.SetRegistrationStatusUseCase
import io.bsu.mmf.helpme.common.usecase.sms.SendSmsUseCase
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
import retrofit2.create
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

val commonModule = module {

    //android
   // single { androidApplication().applicationContext }
    single { createSharedPreference(get()) }

    //dataSources
    single<AuthDataSource>{
        AuthDataSourceImpl(get())
    }

    single<ContactsDataSource>{
        ContactsDataSourceImpl(get(), get(), get())
    }
    single<FireBaseContactsDataSource>{ FireBaseContactsDataSourceImpl(get(), get()) }
    single<SharedPreferenceDataSource> { SharedPreferenceDataSourceImpl(get()) }
    single<WeatherDataSource>{ WeatherDataSourceImpl(get(), get(), get()) }
    single { BaseRemoteDataSource() }

    factory<SendSmsDataSource> { SendSmsDataSourceImpl(get()) }


    //network
    single { createOkHttpClient(get(), get()) }
    single { createOkHttpCache(get()) }
    single { createFile(get()) }
    single { createHttpLoggingInterceptor() }
    single { createRetrofit(get(), get()) }
    single { createUrl() }
    single { createWeatherApi(get()) }

    //dataBase
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "sosapp.db").build() }
    single { get<AppDatabase>().contactsDao() }

    //mappers
    single { AuthResponseToDtoMapper() }
    single { ContactDtoToRoomItemMapper() }
    single { ContactRoomItemToDtoMapper() }
    single { ContactFBToDtoMapper() }
    single { ContactToFBMapper() }
    single { CurrentWeatherToDtoMapper() }

    //repositories
    single<AuthRepository>{ AuthRepositoryImpl(get()) }
    single<SharedPreferenceRepository>{ SharedPreferenceRepositoryImpl(get()) }
    single<WeatherRepository>{ WeatherRepositoryImpl(get()) }
    single<FirebaseContactRepository>{ FirebaseContactRepositoryImpl(get()) }
    single<ContactsLocalRepository>{ ContactsRepository(get()) }
    single<GeocodeRepository>{ GeocodeRepositoryImpl(get()) }
    factory<SendSmsRepository> { SendSmsRepositoryImpl(get()) }

    //useCases


    //auth
    single { CheckUserLoginUseCase(get()) }
    single { CreateAccountUseCase(get()) }
    single { LoginWithEmailUseCase(get()) }
    single { ResetPasswordUseCase(get()) }

    //sharedPreference
    single { GetRegistrationStatusUseCase(get()) }
    single { SetRegistrationStatusUseCase(get()) }

    //weather
    single { GetCurrentWeatherUseCase(get()) }

    //firebase
    single { SaveFBContactUseCase(get()) }

    //contatcs
    factory { GetAllContactUseCase(get()) }
    single { SaveContactUseCase(get()) }
    single { GetAllSourceUseCase(get()) }
    single { DeleteContactUseCase(get()) }
    single { GetContactByIdUseCase(get()) }
    single { UpdateContactUseCase(get()) }

    //geocoder
    single { GetCoordinatesFromAddressUseCase(get()) }

    //sms
    factory { SendSmsUseCase(get(), get()) }

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
        okHttpClient: OkHttpClient
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl(url)
            .addCallAdapterFactory(ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
            .client(okHttpClient)
            .build()
}

fun createUrl() = "http://api.openweathermap.org/data/2.5/"

fun createWeatherApi(
        retrofit: Retrofit
): WeatherApi = retrofit.create()

fun createGeocoder(context: Context) = Geocoder(context, Locale.ENGLISH)

fun createAndroidSmsManager() = SmsManager.getDefault()