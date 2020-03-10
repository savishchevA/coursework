package io.bsu.mmf.helpme.data.repository.weather

import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.weather.CurrentWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather>
}