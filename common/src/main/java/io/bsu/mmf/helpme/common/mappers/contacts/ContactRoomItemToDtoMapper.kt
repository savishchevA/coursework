package io.bsu.mmf.helpme.common.mappers.contacts

import io.bsu.mmf.helpme.common.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Contact





class ContactRoomItemToDtoMapper (
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