package io.bsu.mmf.helpme.common.database

import io.bsu.mmf.helpme.data.entity.SosContact


internal interface KeyValueService {
    fun getSosContact(): SosContact?
    fun getSosMessage(): String?
    fun saveSosContact(contact: SosContact)
    fun saveSosMessage(message: String)
    fun saveCurrentTime(time: Int)
    fun getCurrentTime(): Int
}