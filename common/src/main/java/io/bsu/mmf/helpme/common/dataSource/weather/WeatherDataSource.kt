package io.bsu.mmf.helpme.common.dataSource.weather

import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.weather.CurrentWeather

interface WeatherDataSource {

   suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather>

}