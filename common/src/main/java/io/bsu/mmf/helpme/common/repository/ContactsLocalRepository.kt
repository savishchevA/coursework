package io.bsu.mmf.helpme.common.repository

import androidx.paging.DataSource
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsLocalRepository {

    suspend fun addContactToDb(contact: Contact)
    suspend fun addContactsToDb(contacts: List<Contact>)
    suspend fun getAllContacts(): Flow<List<Contact>>
    suspend fun getPriorityContact(): Contact

    fun getAllSource(): DataSource.Factory<Int, Contact>

    suspend fun deleteContact(contactId: Int)
    suspend fun getContactById(contactId: Int): Contact
    suspend fun updateContact(contact: Contact)

    suspend fun updatePrimaryContact(contactId: Int)
    suspend fun getFlowPrimaryContact(): Flow<Contact>
    fun getAllContactsList(): List<Contact>

    suspend fun getContactsCount(): Int

}