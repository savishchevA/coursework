package io.bsu.mmf.helpme.data.mappers.contacts

import io.bsu.mmf.helpme.data.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.entity.local.Contact




class ContactDtoToRoomItemMapper (
): Mapper<Contact, ContactRoomItem> {
    override fun map(from: Contact): ContactRoomItem {
        return with(from) {
            ContactRoomItem (
                contactName = name,
                contactAddress = address,
                phoneNumber = phoneNumber,
                isPriorityContact = isPriorityContact
            )
        }
    }
}