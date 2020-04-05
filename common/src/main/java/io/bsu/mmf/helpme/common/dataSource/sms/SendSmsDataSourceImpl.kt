package io.bsu.mmf.helpme.common.dataSource.sms

import android.telephony.SmsManager

class SendSmsDataSourceImpl (
    private val smsManager: SmsManager
) : SendSmsDataSource {

    override fun sendSms(message: String, phone: String) {
        val dividedMessage = smsManager.divideMessage(message)
        smsManager.sendMultipartTextMessage(phone, null, dividedMessage, null, null)
    }
}