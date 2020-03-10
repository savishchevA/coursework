package io.bsu.mmf.helpme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.data.usecase.weather.GetCurrentWeatherUseCase
import io.bsu.mmf.helpme.domain.weather.CurrentWeather
import kotlinx.coroutines.launch

class MainViewModel (
        private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather>
    get() = _currentWeather

    init {
        viewModelScope.launch {
            getCurrentWeatherUseCase().fold(
                    onSuccess = {
                        _currentWeather.value = it
                    }
            )
        }
    }


}