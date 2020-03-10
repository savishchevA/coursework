package io.bsu.mmf.helpme.domain.driver

import io.bsu.mmf.helpme.domain.entity.SosContact
import io.reactivex.Completable


interface SmsDriver {
    fun sendSms(contact: SosContact, message: String): Completable
}
