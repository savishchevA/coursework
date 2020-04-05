package io.bsu.mmf.helpme.common.repository.sms

import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSource
import io.bsu.mmf.helpme.common.dataSource.sms.SendSmsDataSourceImpl

class SendSmsRepositoryImpl (
    private val sendSmsDataSource: SendSmsDataSource
) : SendSmsRepository {

    override fun sendSms(message: String, phone: String) {
        sendSmsDataSource.sendSms(message, phone)
    }
}