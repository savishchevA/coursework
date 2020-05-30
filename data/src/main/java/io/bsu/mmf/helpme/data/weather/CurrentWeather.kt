package io.bsu.mmf.helpme.data.weather

data class CurrentWeather(
        val temp: Double,
        val feelTemp: Double,
        val windSpeed: Int,
        val weatherIcon: String
)