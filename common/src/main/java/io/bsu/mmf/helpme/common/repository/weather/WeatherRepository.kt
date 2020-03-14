package io.bsu.mmf.helpme.common.repository.weather

import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.weather.CurrentWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather>
}