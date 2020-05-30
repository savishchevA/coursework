package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.sharedPreference.time.GetAlarmTimeUseCase
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
class ActiveScanningViewModel(
    private val getAlarmTimeUseCase: GetAlarmTimeUseCase
) : ViewModel() {

    private var _currentTimerValue = MutableLiveData<Int>()
    val currentTimerValue: LiveData<Int>
        get() = _currentTimerValue

    private var _alarmTimeValue = MutableLiveData<Event<Long>>()
    val alarmTimeValue: LiveData<Event<Long>>
        get() = _alarmTimeValue

    init {
        _alarmTimeValue.value = Event(getAlarmTimeUseCase().toLong() * 1000)
    }

    fun startTimer() {

        viewModelScope.launch {
            val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 100)

            repeat(11) {
                tickerChannel.receive()
                _currentTimerValue.value = it
            }
        }
    }

}