package io.bsu.mmf.helpme.data.dataSource.weather

import io.bsu.mmf.helpme.data.dataSource.base.BaseRemoteDataSource
import io.bsu.mmf.helpme.data.mappers.weather.CurrentWeatherToDtoMapper
import io.bsu.mmf.helpme.data.network.WeatherApi
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.weather.CurrentWeather
import okhttp3.internal.wait


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

