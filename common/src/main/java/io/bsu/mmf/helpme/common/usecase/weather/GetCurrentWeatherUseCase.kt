package io.bsu.mmf.helpme.common.usecase.weather

import io.bsu.mmf.helpme.common.repository.weather.WeatherRepository
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.weather.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GetCurrentWeatherUseCase (
        private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(): ResultNetwork<CurrentWeather> {
        return withContext(Dispatchers.IO) {
            weatherRepository.getCurrentWeather()
        }
    }

}