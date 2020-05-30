package io.bsu.mmf.helpme.common.repository

import io.bsu.mmf.helpme.common.dataSource.FireBaseContactsDataSource
import io.bsu.mmf.helpme.data.entity.local.Contact


interface FirebaseContactRepository {
    suspend fun saveContact(contact: Contact)
    suspend fun getContact(): Contact
}

class FirebaseContactRepositoryImpl (
        private val fireBaseContactsDataSource: FireBaseContactsDataSource
) : FirebaseContactRepository {

    override suspend fun saveContact(contact: Contact) {
        fireBaseContactsDataSource.saveContact(contact)
    }

    override suspend fun getContact(): Contact {
        return fireBaseContactsDataSource.getContacts()
    }
}