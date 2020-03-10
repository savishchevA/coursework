package io.bsu.mmf.helpme.data.mappers.weather

import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.data.network.response.CurrentWeatherResponse
import io.bsu.mmf.helpme.domain.weather.CurrentWeather



class CurrentWeatherToDtoMapper (
) : Mapper<CurrentWeatherResponse, CurrentWeather> {
    override fun map(from: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            temp =  from.main.temp
        )
    }
}