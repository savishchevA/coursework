package io.bsu.mmf.helpme.featuremain.viewmodel.settings

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.profile.*
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TrainSettingsViewModel(
    private val updateTrainDistanceUseCase: UpdateTrainDistanceUseCase,
    private val updateTrainTimeUseCase: UpdateTrainTimeUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel() {


    private var _navigateUp = MutableLiveData<Event<Unit>>()
    val navigateUp: LiveData<Event<Unit>>
        get() = _navigateUp

    private val _profile = MutableLiveData<Event<Profile>>()
    val profile: LiveData<Event<Profile>>
        get() = _profile

    init {
        viewModelScope.launch {
            getProfileUseCase().collect {
                _profile.value = Event(it)
            }
        }
    }


    fun updateTrainTime(trainTime: String) {
        viewModelScope.launch {
            updateTrainTimeUseCase(trainTime)
        }
    }

    fun updateTrainDistance(text: String) {
        viewModelScope.launch {
            updateTrainDistanceUseCase(text)
        }
    }
}