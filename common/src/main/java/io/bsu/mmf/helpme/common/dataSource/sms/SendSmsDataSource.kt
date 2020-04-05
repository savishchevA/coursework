package io.bsu.mmf.helpme.common.dataSource.sms

interface SendSmsDataSource {
    fun sendSms(message: String, phone: String)
}