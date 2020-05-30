package io.bsu.mmf.helpme.featuremain.viewmodel.settings

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.profile.GetProfileUseCase
import io.bsu.mmf.helpme.common.usecase.profile.UpdateCommonMessageUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.IsSendAllContactsUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.SetSendAllContactsUseCase
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactSettingsViewModel(
    private val isSendAllContactsUseCase: IsSendAllContactsUseCase,
    private val setSendAllContactsUseCase: SetSendAllContactsUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val updateCommonMessageUseCase: UpdateCommonMessageUseCase
) : BaseViewModel() {

    private val _profile = MutableLiveData<Event<Profile>>()
    val profile: LiveData<Event<Profile>>
        get() = _profile

    private val _sendAllContacts = MutableLiveData<Event<Boolean>>()
    val sendAllContacts: LiveData<Event<Boolean>>
        get() = _sendAllContacts


    init {
        viewModelScope.launch {
            getProfileUseCase().collect {
                _profile.value = Event(it)
            }
        }

        viewModelScope.launch {
            _sendAllContacts.value = Event(isSendAllContactsUseCase())
        }
    }

    fun updateCommonMessage(commonMessage: String) {
        viewModelScope.launch {
            updateCommonMessageUseCase(commonMessage)
        }
    }

    fun setSendAllContacts(isSendAllContacts: Boolean) {
        setSendAllContactsUseCase(isSendAllContacts)
    }
}