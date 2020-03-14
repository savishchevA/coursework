package io.bsu.mmf.helpme.common.repository

import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsLocalRepository {

    suspend fun addContactToDb(contact: Contact)
    suspend fun addContactsToDb(contacts: List<Contact>)
    suspend fun getAllContacts(): Flow<List<Contact>>

}