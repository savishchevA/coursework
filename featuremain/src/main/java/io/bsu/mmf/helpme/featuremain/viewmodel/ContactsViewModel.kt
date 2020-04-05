package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.contact.*
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.events.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val getAllContactUseCase: GetAllContactUseCase,
    private val getAllSourceUseCase: GetAllSourceUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModel() {

    private var _allContacts = MutableLiveData<List<Contact>>()
    val allContacts: LiveData<List<Contact>>
        get() = _allContacts

    private var _allContactsSource = MutableLiveData<PagedList<Contact>>()
    val allContactsSource: LiveData<PagedList<Contact>>
        get() = _allContactsSource

    private var _addContactEvent = MutableLiveData<Event<Unit>>()
    val addContactEvent: LiveData<Event<Unit>>
        get() = _addContactEvent

    private var _contactDetailEvent = MutableLiveData<Event<Unit>>()
    val contactDetailEvent: LiveData<Event<Unit>>
        get() = _contactDetailEvent
//
//    val contactsSource by lazy {
//        LivePagedListBuilder<Int, Contact>(
//            getAllSourceUseCase(), 10
//        ).build()
//    }

    init {
        viewModelScope.launch {
            getAllContactUseCase().collect {
                _allContacts.value = it
            }
        }


    }

    fun onEventClickListener(event: ContactListEvents) {
        when (event) {
            is AddContactEvent -> {
                _addContactEvent.value = Event(Unit)
            }

            is ContactDetailEvent -> {
                _contactDetailEvent.value = Event(Unit)
            }
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            deleteContactUseCase(id)
        }
    }

}