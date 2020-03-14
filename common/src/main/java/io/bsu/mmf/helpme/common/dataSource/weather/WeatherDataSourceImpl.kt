package io.bsu.mmf.helpme.common.dataSource.weather

import io.bsu.mmf.helpme.common.dataSource.base.BaseRemoteDataSource
import io.bsu.mmf.helpme.common.mappers.weather.CurrentWeatherToDtoMapper
import io.bsu.mmf.helpme.common.network.WeatherApi
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.weather.CurrentWeather


class WeatherDataSourceImpl (
        private val weatherApi: WeatherApi,
        private val baseRemoteDataSource: BaseRemoteDataSource,
        private val currentWeatherToDtoMapper: CurrentWeatherToDtoMapper
) : WeatherDataSource {

    override suspend fun getCurrentWeather(): ResultNetwork<CurrentWeather> {
        return baseRemoteDataSource.executeResponseWithMapper(
                weatherApi.getWeatherByCity(cityName = "Minsk"),
                currentWeatherToDtoMapper
        )
    }
}

