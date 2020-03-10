package io.bsu.mmf.helpme.data.mappers.contacts

import io.bsu.mmf.helpme.data.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.entity.local.Contact
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ContactRoomItemToDtoMapper @Inject constructor(
) : Mapper<ContactRoomItem, Contact> {
    override fun map(from: ContactRoomItem): Contact {
        return with(from) {
            Contact(
                name = contactName,
                phoneNumber = phoneNumber,
                address = contactAddress ?: "",
                    message = "",
                isPriorityContact = isPriorityContact
            )
        }
    }
}