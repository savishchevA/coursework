package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.contact.SaveContactUseCase
import io.bsu.mmf.helpme.common.usecase.location.GetCoordinatesFromAddressUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.data.location.AddressCoordinate
import kotlinx.coroutines.launch
import timber.log.Timber

class AddContactViewModel(
    private val saveContactUseCase: SaveContactUseCase,
    private val getCoordinatesFromAddressUseCase: GetCoordinatesFromAddressUseCase
) : ViewModel() {

    private val _savedContact = MutableLiveData<Event<Unit>>()
    val successSavingContact: LiveData<Event<Unit>>
        get() = _savedContact

    private val _addressCoordinate = MutableLiveData<AddressCoordinate>()
    val addressCoordinate: LiveData<AddressCoordinate>
        get() = _addressCoordinate


    fun getAddressCoordinate(address: String) {
        viewModelScope.launch {
            getCoordinatesFromAddressUseCase(address).fold(
                onSuccess = {
                    _addressCoordinate.value = it
                    Timber.e(it.toString())
                },
                onFailure = {
                    Timber.e(it.errorMessage)
                }
            )

        }
    }

    fun saveContact(contact: Contact) {


        viewModelScope.launch {
            saveContactUseCase(contact)
            _savedContact.value = Event(Unit)
        }
    }
}