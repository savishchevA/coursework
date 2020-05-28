package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.contact.*
import io.bsu.mmf.helpme.common.usecase.location.GetCoordinatesFromAddressUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.data.location.AddressCoordinate
import kotlinx.coroutines.launch
import timber.log.Timber

class ChangeContactViewModel (
    private val getContactUseCase: GetContactByIdUseCase,
    private val getCoordinatesFromAddressUseCase: GetCoordinatesFromAddressUseCase,
    private val updateContactUseCase: UpdateContactUseCase
): ViewModel() {

    private val _addressCoordinate = MutableLiveData<AddressCoordinate>()
    val addressCoordinate: LiveData<AddressCoordinate>
        get() = _addressCoordinate

    private val _currentContact = MutableLiveData<Contact>()
    val currentContact: LiveData<Contact>
        get() = _currentContact

    private val _updateContactEvent = MutableLiveData<Event<Unit>>()
    val updateContactEvent: LiveData<Event<Unit>>
        get() = _updateContactEvent

    fun getAddressCoordinate(address: String) {
        viewModelScope.launch {
            getCoordinatesFromAddressUseCase(address).fold (
                onSuccess = {
                    _addressCoordinate.value = it

                },
                onFailure = {
                    Timber.e(it.errorMessage)
                }
            )
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            updateContactUseCase(contact)
            _updateContactEvent.value = Event(Unit)
        }
    }

    fun getCurrentContact(contactId: Int) {
        viewModelScope.launch {
            _currentContact.value = getContactUseCase(contactId)
        }
    }

}