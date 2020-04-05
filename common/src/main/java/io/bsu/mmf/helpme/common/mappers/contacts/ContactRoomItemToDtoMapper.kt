package io.bsu.mmf.helpme.common.mappers.contacts

import io.bsu.mmf.helpme.common.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Contact


class ContactRoomItemToDtoMapper(
) : Mapper<ContactRoomItem, Contact> {
    override fun map(from: ContactRoomItem): Contact {
        return with(from) {
            Contact(
                id = id,
                name = contactName,
                phoneNumber = phoneNumber,
                address = contactAddress ?: "",
                message = "",
                longitude = longitude ?: 0.0,
                latitude = latitude ?: 0.0,
                contactImage = image,
                isPriorityContact = isPriorityContact
            )
        }
    }
}