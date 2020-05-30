package io.bsu.mmf.helpme.common.usecase.contact

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetContactsCountUseCase (
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke(): Int {
        return withContext(Dispatchers.IO) {
            contactsLocalRepository.getContactsCount()
        }
    }

}