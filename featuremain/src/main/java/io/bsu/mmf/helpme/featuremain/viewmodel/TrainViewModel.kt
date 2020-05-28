package io.bsu.mmf.helpme.featuremain.viewmodel

import android.location.Location
import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.profile.GetProfileUseCase
import io.bsu.mmf.helpme.common.usecase.train.SaveTrainUseCase
import io.bsu.mmf.helpme.data.entity.local.Profile
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class TrainViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val saveTrainUseCase: SaveTrainUseCase
) : ViewModel() {
    private var currentTime: Int = 0
    private var prevTime: Int = 0
    private var delta = 0
    private var distance = 0f
    private var currentSpeed = 0f
    private var startLocation: Location? = null
    private var prevLocation: Location? = null
    private var checkLocation: Location? = null

    private var tickerChannel: ReceiveChannel<Unit>? = null

    private val _checkDistanceLimit = MediatorLiveData<Boolean>()
    val checkDistanceLimit: LiveData<Boolean>
        get() = _checkDistanceLimit

    private val _checkTimeLimit = MutableLiveData<Event<String>>()
    val checkTimeLimit: LiveData<Event<String>>
        get() = _checkTimeLimit

    init {
        startTimer()

        viewModelScope.launch {
            getProfileUseCase().collect {
                _profile.value = it
            }
        }

        _checkDistanceLimit.addSource(profile) {
            if (it.trainDistance.isNotEmpty()) {
                _checkDistanceLimit.value = it.trainDistance.toFloat() > distance
            }
        }
    }

    private val _finishTrain = MutableLiveData<Event<Unit>>()
    val finishTrain: LiveData<Event<Unit>>
        get() = _finishTrain

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile



    private val _showCurrentTime = MutableLiveData<Event<Int>>()
    val showCurrentTime: LiveData<Event<Int>>
        get() = _showCurrentTime

    private val _showCurrentDistance = MutableLiveData<Event<Float>>()
    val showCurrentDistance: LiveData<Event<Float>>
        get() = _showCurrentDistance

    private val _showCurrentSpeed = MutableLiveData<Event<Float>>()
    val showCurrentSpeed: LiveData<Event<Float>>
        get() = _showCurrentSpeed

    private val _drawPartOfDistance = MutableLiveData<Event<Pair<Location, Location>>>()
    val drawPartOfDistance: LiveData<Event<Pair<Location, Location>>>
        get() = _drawPartOfDistance


    fun saveTrain(trainItem: TrainItem) {
        viewModelScope.launch {
            saveTrainUseCase(trainItem)
        }
    }

    fun setCurrentLocation(location: Location) {
        //init startLocation
        if (startLocation == null) {
            startLocation = location
        }

        //init current Location and time in second
        currentTime = Calendar.getInstance().get(Calendar.SECOND)



        if (prevTime != 0) {
            delta = currentTime - prevTime
            prevTime = currentTime
            distance = startLocation?.distanceTo(location) ?: 0f

            currentSpeed = prevLocation?.distanceTo(location)?.div(delta) ?: 0f

        }
        prevTime = currentTime
        prevLocation = location

        _showCurrentDistance.value = Event(distance)
        if (currentSpeed <= 0f) {
            _showCurrentSpeed.value = Event(0f)
        } else {
            _showCurrentSpeed.value = Event(currentSpeed * 3.6f)

        }
        if (prevLocation != null) {
            _drawPartOfDistance.value = Event(prevLocation!! to location)
        }

    }

    private fun checkMoving() {

        if (prevLocation != null && checkLocation != null) {
            if (checkLocation?.distanceTo(prevLocation)!! < 50f) {
                _finishTrain.value = Event(Unit)
            } else {
                startTimer()
            }
        } else {
            startTimer()
        }

    }


    private fun timerDelay() {
        viewModelScope.launch {
            delay(20000)
            checkMoving()
        }
    }


    private fun startTimer() {
        tickerChannel?.cancel()
        checkLocation = prevLocation
        tickerChannel = ticker(
            delayMillis = 1000,
            initialDelayMillis = 1000,
            context = viewModelScope.coroutineContext
        )

        timerDelay()
    }


    fun cancelTimer() {
        tickerChannel?.cancel()
    }

}