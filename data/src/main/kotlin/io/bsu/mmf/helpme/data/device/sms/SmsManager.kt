package io.bsu.mmf.helpme.data.device.sms

import io.bsu.mmf.helpme.domain.driver.SmsDriver
import io.bsu.mmf.helpme.domain.entity.SosContact
import io.reactivex.Completable
import javax.inject.Inject


class SmsManager @Inject constructor(private val smsService: SmsService): SmsDriver {
    override fun sendSms(contact: SosContact, message: String): Completable {
        return Completable.fromRunnable {
            smsService.send(contact.contactInfo, message)
        }
    }
}
