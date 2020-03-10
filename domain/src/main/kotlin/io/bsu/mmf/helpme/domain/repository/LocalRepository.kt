package io.bsu.mmf.helpme.domain.repository

import io.bsu.mmf.helpme.domain.entity.SosContact


interface LocalRepository {
    fun hasConfig(): Boolean

    fun fetchContact(): SosContact?
    fun saveContact(sosContact: SosContact)

    fun fetchMessage(): String?
    fun saveMessage(message: String)

    fun saveTime(time: Int)
    fun getTime(): Int?
}
