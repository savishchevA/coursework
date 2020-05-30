package io.bsu.mmf.helpme.featuremain.viewmodel.settings

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.contact.*
import io.bsu.mmf.helpme.common.usecase.profile.GetProfileUseCase
import io.bsu.mmf.helpme.common.usecase.profile.UpdateAlarmTimeUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.time.SetAlarmTimeUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileSettingsViewModel (
    private val getAllContactUseCase: GetAllContactUseCase,
    private val updatePrimaryContactUseCase: UpdatePrimaryContactUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getObservablePrimaryContactUseCase: GetObservablePrimaryContactUseCase,
    private val updateAlarmTimeUseCase: UpdateAlarmTimeUseCase,
    private val setAlarmTimeUseCase: SetAlarmTimeUseCase
): BaseViewModel() {

    private val _profile = MutableLiveData<Event<Profile>>()
    val profile: LiveData<Event<Profile>>
        get() = _profile
    private var _allContacts = MutableLiveData<List<Contact>>()
    val allContacts: LiveData<List<Contact>>
        get() = _allContacts

    private var _primaryContact = MutableLiveData<Contact>()
    val primaryContact: LiveData<Contact>
        get() = _primaryContact

    init {
        viewModelScope.launch {
            getAllContactUseCase().collect {
                _allContacts.value = it
            }
        }

        viewModelScope.launch {
            getObservablePrimaryContactUseCase().collect {
                _primaryContact.value = it
            }
        }

        viewModelScope.launch {
            getProfileUseCase().collect {
                _profile.value = Event(it)
            }
        }
    }

    fun updateAlarmTime(alarmTime: String) {
        viewModelScope.launch {
            updateAlarmTimeUseCase(alarmTime)
            setAlarmTimeUseCase(alarmTime)
        }
    }

    fun onContactClick(contact: Contact) {
        viewModelScope.launch {
            updatePrimaryContactUseCase(contactId = contact.id)
        }
    }

}

