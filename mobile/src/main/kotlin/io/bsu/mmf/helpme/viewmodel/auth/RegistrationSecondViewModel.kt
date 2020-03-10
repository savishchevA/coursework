package io.bsu.mmf.helpme.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.data.usecase.SaveFBContactUseCase
import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.utils.Event
import kotlinx.coroutines.launch

class RegistrationSecondViewModel(
        private val saveFBContactUseCase: SaveFBContactUseCase
): ViewModel() {

    private val _navigateToMainScreen = MutableLiveData<Event<Unit>>()
    val navigateToMainScreen: LiveData<Event<Unit>>
        get() = _navigateToMainScreen

    fun saveContact(contact: Contact) {
        viewModelScope.launch {
            saveFBContactUseCase(contact)
            _navigateToMainScreen.value = Event(Unit)
        }
    }
}
