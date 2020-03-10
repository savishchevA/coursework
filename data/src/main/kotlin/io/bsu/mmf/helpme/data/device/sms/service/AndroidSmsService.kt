package io.bsu.mmf.helpme.data.device.sms.service

import android.telephony.SmsManager
import io.bsu.mmf.helpme.data.device.sms.SmsService
import timber.log.Timber



class AndroidSmsService (private val smsManager: SmsManager) : SmsService {
    override fun send(to: String, message: String) {
        val dividedMessage = smsManager.divideMessage(message)
        Timber.v("Sending message >>>%s<<< to %s", message, to)
        smsManager.sendMultipartTextMessage(to, null, dividedMessage, null, null)
    }
}
