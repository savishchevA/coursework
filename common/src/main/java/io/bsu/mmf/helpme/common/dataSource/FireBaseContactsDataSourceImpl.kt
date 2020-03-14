package io.bsu.mmf.helpme.common.dataSource

import com.google.firebase.database.FirebaseDatabase
import io.bsu.mmf.helpme.common.mappers.firebase.ContactFBToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactToFBMapper
import io.bsu.mmf.helpme.data.entity.local.Contact
import timber.log.Timber


class FireBaseContactsDataSourceImpl (
    private val contactFBToDtoMapper: ContactFBToDtoMapper,
    private val contactToFBMapper: ContactToFBMapper
) : FireBaseContactsDataSource {

    private val database = FirebaseDatabase.getInstance().reference

    override suspend fun saveContact(contact: Contact) {
        database.child("contact").setValue(
            contactToFBMapper.map(contact)
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Timber.e("From datasource: success")
            } else {
                Timber.e("Fail")
            }
        }
    }

    override suspend fun getContacts(contacts: List<Contact>) {

    }
}