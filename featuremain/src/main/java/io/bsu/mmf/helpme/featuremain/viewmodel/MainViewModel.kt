package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.common.usecase.contact.GetAllContactUseCase
import io.bsu.mmf.helpme.common.usecase.contact.SaveContactUseCase
import io.bsu.mmf.helpme.common.usecase.weather.GetCurrentWeatherUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.data.weather.CurrentWeather
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getAllContactUseCase: GetAllContactUseCase,
    private val saveContactUseCase: SaveContactUseCase
) : BaseViewModel() {

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather>
        get() = _currentWeather


    private val _trainList = MutableLiveData<List<TrainItem>>()
    val trainList: LiveData<List<TrainItem>>
        get() = _trainList

    private val trainListData = emptyList<TrainItem>()

//    private val trainListData = listOf<TrainItem>(
//        TrainItem(
//            name = "Тренировка №1",
//            day = "20.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №2",
//            day = "21.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:50"
//        ), TrainItem(
//            name = "Тренировка №3",
//            day = "22.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №4",
//            day = "23.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:15"
//        ), TrainItem(
//            name = "Тренировка №5",
//            day = "25.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №6",
//            day = "20.05",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        ), TrainItem(
//            name = "Тренировка №7",
//            day = "20.10",
//            distance = "14.55",
//            status = "Complete",
//            time = "14:55"
//        )
//    )

    init {

    //    viewModelScope.launch {
//            repeat(1000) {
//                addTestContact("New contact №$it")
//            }
//            getAllContactUseCase().collect {
//                if (it.isEmpty()) {
//                    repeat(1000) {
//                        addTestContact("New contact №$it")
//                    }
//
//                }
//                Timber.e("Is empty contacts: ${it.isEmpty()}")
//            }
//        }

       _trainList.value = trainListData
        getCurrentWeather()
    }

    private fun addTestContact(name: String) {

        viewModelScope.launch {
            saveContactUseCase(
                Contact(
                    name = name,
                    address = "Test address",
                    phoneNumber = "345678765432",
                    message = "Test text message",
                    isPriorityContact = false
                )
            )
        }

    }

    private fun getCurrentWeather() {
        execute(
            call = {
                getCurrentWeatherUseCase()
            },
            result = _currentWeather
        )
    }

    fun triggeredEvent() {}

}