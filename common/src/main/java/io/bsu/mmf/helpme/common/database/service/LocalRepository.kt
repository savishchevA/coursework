package io.bsu.mmf.helpme.common.database.service

import io.bsu.mmf.helpme.data.entity.SosContact


interface LocalRepository {
    fun hasConfig(): Boolean

    fun fetchContact(): SosContact?
    fun saveContact(sosContact: SosContact)

    fun fetchMessage(): String?
    fun saveMessage(message: String)

    fun saveTime(time: Int)
    fun getTime(): Int?
}
