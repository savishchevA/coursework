package io.bsu.mmf.helpme.common.dataSource

import com.google.firebase.database.*
import io.bsu.mmf.helpme.common.dto.ContactFb
import io.bsu.mmf.helpme.common.mappers.firebase.ContactFBToDtoMapper
import io.bsu.mmf.helpme.common.mappers.firebase.ContactToFBMapper
import io.bsu.mmf.helpme.data.entity.local.Contact
import timber.log.Timber


class FireBaseContactsDataSourceImpl(
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

    override suspend fun getContacts(): Contact {

        var contact: Contact? = null

        FirebaseDatabase.getInstance().getReference("contact").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) = Unit
            override fun onChildMoved(p0: DataSnapshot, p1: String?) = Unit
            override fun onChildChanged(p0: DataSnapshot, p1: String?) = Unit
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val contactFm = p0.getValue(ContactFb::class.java)
                Timber.e(contactFm.toString())
                if (contactFm != null) {
                    contact = contactFBToDtoMapper.map(contactFm)
                }

            }

            override fun onChildRemoved(p0: DataSnapshot) = Unit

        })

        return contact!!

    }
}