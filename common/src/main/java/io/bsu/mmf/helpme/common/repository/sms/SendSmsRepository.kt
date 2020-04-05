package io.bsu.mmf.helpme.common.repository.sms

interface SendSmsRepository {
    fun sendSms(message: String, phone: String)
}