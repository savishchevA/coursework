package io.bsu.mmf.helpme.common.mappers.weather

import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.common.network.response.CurrentWeatherResponse
import io.bsu.mmf.helpme.data.weather.CurrentWeather



class CurrentWeatherToDtoMapper (
) : Mapper<CurrentWeatherResponse, CurrentWeather> {
    override fun map(from: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            temp =  from.main.temp
        )
    }
}