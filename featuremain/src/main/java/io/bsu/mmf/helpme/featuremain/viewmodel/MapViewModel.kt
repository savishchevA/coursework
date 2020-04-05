package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import io.bsu.mmf.helpme.common.usecase.contact.GetAllContactUseCase
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MapViewModel(
    private val getAllContactUseCase: GetAllContactUseCase
) : ViewModel() {

    private var _allContacts = MutableLiveData<List<Contact>>()
    val allContact: LiveData<List<Contact>>
        get() = _allContacts

    init {
        viewModelScope.launch {
            getAllContactUseCase().collect {
                _allContacts.value = it
            }
        }
    }

}