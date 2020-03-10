package io.bsu.mmf.helpme.domain.usecase.contacts

import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository
import javax.inject.Inject

class SaveAllContactsUseCase @Inject constructor(
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contactsList: List<Contact>){
        contactsLocalRepository.addContactsToDb(contactsList)
    }

}