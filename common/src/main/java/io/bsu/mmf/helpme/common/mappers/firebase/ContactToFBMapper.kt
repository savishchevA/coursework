package io.bsu.mmf.helpme.common.mappers.firebase

import io.bsu.mmf.helpme.common.dto.ContactFb
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Contact




class ContactToFBMapper (
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