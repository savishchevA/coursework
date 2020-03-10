package io.bsu.mmf.helpme.data.di.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @Singleton
    @Provides
    fun providesRetrofit(
            @Named("base") url: String,
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
//
//    @Singleton
//    @Provides
//    fun providesGson(): Gson {
//        val gsonBuilder = GsonBuilder()
//        gsonBuilder.setLenient()
//        gsonBuilder.setFieldNamingPolicy((FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES))
//        return gsonBuilder.create()
//    }
//
//    @Singleton
//    @Provides
//    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
//            GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    @Named("base")
    fun providesUrl() = "http://api.openweathermap.org/data/2.5/"

}