package io.bsu.mmf.helpme.data.network

import io.bsu.mmf.helpme.data.network.response.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("weather")
    fun getWeatherByCity(
            @Query("q") cityName: String,
            @Query("appid") appid: String = "fab7be6092d46b8e189c11ade2246b99"
    ): Call<CurrentWeatherResponse>

}