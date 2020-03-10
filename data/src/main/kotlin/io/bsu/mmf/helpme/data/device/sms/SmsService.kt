package io.bsu.mmf.helpme.data.device.sms


interface SmsService {
    fun send(to: String, message: String)
}
