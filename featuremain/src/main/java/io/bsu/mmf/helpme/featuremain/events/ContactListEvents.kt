package io.bsu.mmf.helpme.featuremain.events

import io.bsu.mmf.helpme.data.entity.local.Contact

typealias ContactListEventListener = (ContactListEvents) -> Unit

sealed class ContactListEvents
object AddContactEvent : ContactListEvents()
data class ContactDetailEvent(val contact: Contact) : ContactListEvents()