package io.bsu.mmf.helpme.data.database

import io.bsu.mmf.helpme.domain.entity.SosContact
import io.bsu.mmf.helpme.domain.repository.LocalRepository




internal class PersistentRepository (private val keyValueService: KeyValueService) : LocalRepository {
    override fun hasConfig() =
            (keyValueService.getSosContact() != null && keyValueService.getSosMessage() != null)

    override fun saveContact(sosContact: SosContact) = keyValueService.saveSosContact(sosContact)

    override fun fetchContact() = keyValueService.getSosContact()

    override fun fetchMessage() = keyValueService.getSosMessage()

    override fun saveMessage(message: String) = keyValueService.saveSosMessage(message)

    override fun getTime(): Int? = keyValueService.getCurrentTime()

    override fun saveTime(time: Int) = keyValueService.saveCurrentTime(time)
}
