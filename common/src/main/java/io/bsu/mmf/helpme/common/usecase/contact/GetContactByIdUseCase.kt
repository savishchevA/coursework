package io.bsu.mmf.helpme.common.usecase.contact

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetContactByIdUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contactId: Int): Contact {
        return withContext(Dispatchers.IO) {
            contactsLocalRepository.getContactById(contactId)
        }
    }

}