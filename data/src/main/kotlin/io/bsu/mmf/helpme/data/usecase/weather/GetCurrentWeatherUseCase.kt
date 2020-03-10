package io.bsu.mmf.helpme.data.usecase.weather

import io.bsu.mmf.helpme.data.repository.weather.WeatherRepository
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.weather.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
        private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(): ResultNetwork<CurrentWeather>{
        return withContext(Dispatchers.IO) {
            weatherRepository.getCurrentWeather()
        }
    }

}