package io.bsu.mmf.helpme.data.dataSource.weather

import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.weather.CurrentWeather

interface WeatherDataSource {

   suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather>

}