package io.bsu.mmf.helpme.data.dataSource

import com.google.firebase.database.FirebaseDatabase
import io.bsu.mmf.helpme.data.mappers.firebase.ContactFBToDtoMapper
import io.bsu.mmf.helpme.data.mappers.firebase.ContactToFBMapper
import io.bsu.mmf.helpme.domain.entity.local.Contact
import timber.log.Timber
import javax.inject.Inject

class FireBaseContactsDataSourceImpl @Inject constructor(
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