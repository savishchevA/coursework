package io.bsu.mmf.helpme.domain.repository

import io.bsu.mmf.helpme.domain.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsLocalRepository {

    suspend fun addContactToDb(contact: Contact)
    suspend fun addContactsToDb(contacts: List<Contact>)
    suspend fun getAllContacts(): Flow<List<Contact>>

}