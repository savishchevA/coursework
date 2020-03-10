package io.bsu.mmf.helpme.data.mappers.firebase

import io.bsu.mmf.helpme.data.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.data.dto.ContactFb
import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.entity.local.Contact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactToFBMapper @Inject constructor(
): Mapper<Contact, ContactFb> {
    override fun map(from: Contact): ContactFb {
        return with(from) {
            ContactFb (
                    username = name,
                    address = address,
                    phone = phoneNumber,
                    message = message,
                    isPriorityContact = isPriorityContact
            )
        }
    }
}