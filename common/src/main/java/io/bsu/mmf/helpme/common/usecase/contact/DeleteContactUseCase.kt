package io.bsu.mmf.helpme.common.usecase.contact

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class DeleteContactUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(contactId: Int) {
        withContext(Dispatchers.IO) {
            Timber.e("ContactId :$contactId")
            contactsLocalRepository.deleteContact(contactId)
        }
    }

}