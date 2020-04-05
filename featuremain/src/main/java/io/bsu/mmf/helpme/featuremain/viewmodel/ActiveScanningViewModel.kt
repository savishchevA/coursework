package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
class ActiveScanningViewModel : ViewModel() {

    private var _currentTimerValue = MutableLiveData<Int>()
    val currentTimerValue: LiveData<Int>
        get() = _currentTimerValue

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