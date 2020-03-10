package io.bsu.mmf.helpme.domain.usecase.contacts

import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository


class SaveAllContactsUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contactsList: List<Contact>){
        contactsLocalRepository.addContactsToDb(contactsList)
    }

}