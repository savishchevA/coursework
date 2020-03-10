package io.bsu.mmf.helpme.domain.usecase.contacts

import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




class SaveContactUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactsLocalRepository.addContactToDb(contact)
        }
    }

}