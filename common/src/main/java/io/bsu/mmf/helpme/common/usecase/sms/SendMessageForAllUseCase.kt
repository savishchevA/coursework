package io.bsu.mmf.helpme.common.usecase.sms

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class SendMessageForAllUseCase(
    private val sendSmsRepository: SendSmsRepository,
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            contactsLocalRepository.getAllContacts().collect {
                it.forEach { contact ->
                    sendSmsRepository.sendSms(contact.message, contact.phoneNumber)
                }
            }
        }
    }

}