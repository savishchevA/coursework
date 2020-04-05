package io.bsu.mmf.helpme.common.sensors

import androidx.paging.DataSource
import io.bsu.mmf.helpme.common.dataSource.ContactsDataSource
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.common.repository.ContactsLocalRepository
import kotlinx.coroutines.flow.Flow


class ContactsRepository (
    private val contactsDataSource: ContactsDataSource
) : ContactsLocalRepository {

    override suspend fun addContactToDb(contact: Contact) {
        contactsDataSource.insert(contact)
    }

    override suspend fun addContactsToDb(contacts: List<Contact>) {
        contactsDataSource.insert(contacts)
    }

    override suspend fun getAllContacts(): Flow<List<Contact>> {
        return contactsDataSource.getAll()
    }

    override suspend fun getPriorityContact(): Contact {
        return contactsDataSource.getPriorityContact()
    }

    override fun getAllSource(): DataSource.Factory<Int, Contact> {
        return contactsDataSource.getAllSource()
    }

    override suspend fun deleteContact(contactId: Int) {
        contactsDataSource.deleteContactById(contactId)
    }

    override suspend fun getContactById(contactId: Int): Contact {
       return contactsDataSource.getContactById(contactId)
    }

    override suspend fun updateContact(contact: Contact) {
        contactsDataSource.update(contact)
    }
}