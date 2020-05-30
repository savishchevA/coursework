package io.bsu.mmf.helpme.common.usecase.contact

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdatePrimaryContactUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contactId: Int) {
        withContext(Dispatchers.IO) {
            contactsLocalRepository.updatePrimaryContact(contactId)
        }
    }

}