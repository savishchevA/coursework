package io.bsu.mmf.helpme.common.mappers.weather

import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.common.network.response.CurrentWeatherResponse
import io.bsu.mmf.helpme.data.weather.CurrentWeather



class CurrentWeatherToDtoMapper (
) : Mapper<CurrentWeatherResponse, CurrentWeather> {
    override fun map(from: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            temp =  from.main?.temp ?: 0.0,
            feelTemp = from.main?.feelsLike ?: 0.0,
            windSpeed = from.wind?.speed?.toInt() ?: 0,
            weatherIcon = from.weather?.get(0)?.icon.orEmpty()
        )
    }
}