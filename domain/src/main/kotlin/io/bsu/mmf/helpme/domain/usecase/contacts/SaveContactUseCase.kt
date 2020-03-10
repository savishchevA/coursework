package io.bsu.mmf.helpme.domain.usecase.contacts

import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveContactUseCase @Inject constructor(
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactsLocalRepository.addContactToDb(contact)
        }
    }

}