package io.bsu.mmf.helpme.common.dataSource

import io.bsu.mmf.helpme.data.entity.local.Contact

interface FireBaseContactsDataSource {

    suspend fun saveContact(contact: Contact)
    suspend fun getContacts(contacts: List<Contact>)

}