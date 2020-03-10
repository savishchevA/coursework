package io.bsu.mmf.helpme.data.sensors

import io.bsu.mmf.helpme.data.dataSource.ContactsDataSource
import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.domain.repository.ContactsLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepository @Inject constructor(
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
}