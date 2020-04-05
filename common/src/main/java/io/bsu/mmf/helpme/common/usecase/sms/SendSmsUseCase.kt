package io.bsu.mmf.helpme.common.usecase.sms

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendSmsUseCase(
    private val sendSmsRepository: SendSmsRepository,
    private val contactsLocalRepository: ContactsLocalRepository
) {

    suspend operator fun invoke() {

        withContext(Dispatchers.IO) {
            val contact = contactsLocalRepository.getPriorityContact()
            sendSmsRepository.sendSms(contact.message, contact.phoneNumber)
        }

    }

}