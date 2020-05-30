package io.bsu.mmf.helpme.common.usecase.sms

import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository
import io.bsu.mmf.helpme.common.repository.sms.SendSmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import timber.log.Timber

class SendMessageForAllUseCase(
    private val sendSmsRepository: SendSmsRepository,
    private val contactsLocalRepository: ContactsLocalRepository,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    suspend operator fun invoke() {
        Timber.e("In send message")
        withContext(Dispatchers.IO) {

            if (sharedPreferenceRepository.isSendAllContacts()) {

                contactsLocalRepository.getAllContacts().collect {
                    it.forEach { contact ->
                        sendSmsRepository.sendSms(contact.message, contact.phoneNumber)
                    }
                }
            } else {
                val contact = contactsLocalRepository.getPriorityContact()
                sendSmsRepository.sendSms(contact.message, contact.phoneNumber)
            }



        }
    }

}