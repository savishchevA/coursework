package io.bsu.mmf.helpme.common.repository.weather

import io.bsu.mmf.helpme.common.dataSource.weather.WeatherDataSource
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.weather.CurrentWeather


class WeatherRepositoryImpl (
        private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather> {
        return weatherDataSource.getCurrentWeather()
    }
}