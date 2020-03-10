package io.bsu.mmf.helpme.domain.usecase.contacts

import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(): Flow<List<Contact>> {
        return withContext(Dispatchers.IO) {
            contactsLocalRepository.getAllContacts()
        }
    }

}