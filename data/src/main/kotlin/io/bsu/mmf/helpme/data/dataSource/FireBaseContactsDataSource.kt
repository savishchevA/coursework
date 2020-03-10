package io.bsu.mmf.helpme.data.dataSource

import android.provider.ContactsContract
import io.bsu.mmf.helpme.domain.entity.local.Contact

interface FireBaseContactsDataSource {

    suspend fun saveContact(contact: Contact)
    suspend fun getContacts(contacts: List<Contact>)

}