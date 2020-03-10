package io.bsu.mmf.helpme.data.mappers.firebase

import io.bsu.mmf.helpme.data.dto.ContactFb
import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.entity.local.Contact




class ContactFBToDtoMapper (
): Mapper<ContactFb, Contact> {
    override fun map(from: ContactFb): Contact {
        return with(from) {
            Contact (
                    name = username ?: "",
                    address = address ?: "",
                    phoneNumber = phone ?: "",
                    message = message ?: "",
                    isPriorityContact = isPriorityContact ?: false
            )
        }
    }
}