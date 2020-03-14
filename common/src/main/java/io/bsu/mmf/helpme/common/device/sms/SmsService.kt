package io.bsu.mmf.helpme.common.device.sms


interface SmsService {
    fun send(to: String, message: String)
}
