package io.bsu.mmf.helpme.common.mappers.firebase

import io.bsu.mmf.helpme.common.dto.ContactFb
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Contact




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