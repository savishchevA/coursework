package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.common.usecase.weather.GetCurrentWeatherUseCase
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.data.weather.CurrentWeather
import kotlinx.coroutines.launch

class MainViewModel(
        private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather>
        get() = _currentWeather


    private val _trainList = MutableLiveData<List<TrainItem>>()
    val trainList: LiveData<List<TrainItem>>
        get() = _trainList

    private val trainListData = listOf<TrainItem>(
            TrainItem(
                    name = "Тренировка №1",
                    day = "20/10/20",
                    distance = "14.55",
                    status = "Complete",
                    time = "14:55"
            ), TrainItem(
            name = "Тренировка №2",
            day = "21/10/20",
            distance = "14.55",
            status = "Complete",
            time = "14:50"
    ), TrainItem(
            name = "Тренировка №3",
            day = "22/10/20",
            distance = "14.55",
            status = "Complete",
            time = "14:55"
    ), TrainItem(
            name = "Тренировка №4",
            day = "23/10/20",
            distance = "14.55",
            status = "Complete",
            time = "14:15"
    ), TrainItem(
            name = "Тренировка №5",
            day = "25/10/20",
            distance = "14.55",
            status = "Complete",
            time = "14:55"
    ), TrainItem(
            name = "Тренировка №6",
            day = "20/05/20",
            distance = "14.55",
            status = "Complete",
            time = "14:55"
    ), TrainItem(
            name = "Тренировка №7",
            day = "20/10/20",
            distance = "14.55",
            status = "Complete",
            time = "14:55"
    )
    )

    init {

        _trainList.value = trainListData
        getCurrentWeather()
    }

   private fun getCurrentWeather() {
        viewModelScope.launch {
            getCurrentWeatherUseCase().fold(
                    onSuccess = {
                        _currentWeather.value = it
                    }
            )
        }
    }

    fun triggeredEvent(){}

}