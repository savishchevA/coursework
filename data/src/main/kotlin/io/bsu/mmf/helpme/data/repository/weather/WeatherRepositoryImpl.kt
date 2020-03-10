package io.bsu.mmf.helpme.data.repository.weather

import io.bsu.mmf.helpme.data.dataSource.weather.WeatherDataSource
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.weather.CurrentWeather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather> {
        return weatherDataSource.getCurrentWeather()
    }
}