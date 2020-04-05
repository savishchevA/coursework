package io.bsu.mmf.helpme.featureregistration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.SaveFBContactUseCase
import io.bsu.mmf.helpme.common.usecase.contact.SaveContactUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.launch

class RegistrationSecondViewModel(
        private val saveFBContactUseCase: SaveFBContactUseCase,
        private val saveContactUseCase: SaveContactUseCase
): ViewModel() {

    private val _navigateToMainScreen = MutableLiveData<Event<Unit>>()
    val navigateToMainScreen: LiveData<Event<Unit>>
        get() = _navigateToMainScreen

    fun saveContact(contact: Contact) {
        viewModelScope.launch {
            saveFBContactUseCase(contact)
            saveContactUseCase(contact)
            _navigateToMainScreen.value = Event(Unit)
        }
    }
}
