package io.bsu.mmf.helpme.common.database

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import io.bsu.mmf.helpme.common.database.db.AppDatabase
import io.bsu.mmf.helpme.common.mappers.contacts.ContactDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactRoomItemToDtoMapper
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.*
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.*

@ExperimentalCoroutinesApi
@SmallTest
@RunWith(AndroidJUnit4::class)
class ContactDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var database: AppDatabase
    private lateinit var contactDtoToRoomItemMapper: ContactDtoToRoomItemMapper
    private lateinit var contactRoomItemToDtoMapper: ContactRoomItemToDtoMapper

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)


        contactDtoToRoomItemMapper = ContactDtoToRoomItemMapper()
        contactRoomItemToDtoMapper = ContactRoomItemToDtoMapper()

        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java
            ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()
    }

    @Test
    fun insertContact_retrieveContact() = runBlocking {
        val contact = Contact(
            name = "name",
            phoneNumber = "+375291112233",
            address = "Test street",
            message = "test message",
            isPriorityContact = false
        )

        database.contactsDao().insert(
            contactDtoToRoomItemMapper.map(contact)
        )

        val dbContact = contactRoomItemToDtoMapper.map(
            database.contactsDao().getContactByName(contact.name)
        )

        assertThat(dbContact as Contact, notNullValue())
        assertThat(dbContact.name, `is`(contact.name))
        assertThat(dbContact.phoneNumber, `is`(contact.phoneNumber))
        assertThat(dbContact.address, `is`(contact.address))
        assertThat(dbContact.isPriorityContact, `is`(contact.isPriorityContact))


    }

//    @Test
//    fun updateContact_retrieveContact() = runBlockingTest {
//        val contact = Contact(
//            name = "name",
//            phoneNumber = "+375291112233",
//            address = "Test street",
//            message = "test message",
//            isPriorityContact = false
//        )
//
//        database.contactsDao().insert(
//            contactDtoToRoomItemMapper.map(contact)
//        )
//
//        val dbContact = database.contactsDao().getContactByName(contact.name)
//
//
//        val updatedContact = Contact(
//            id = dbContact.id,
//            name = "Updated Contact",
//            phoneNumber = dbContact.phoneNumber,
//            address = dbContact.contactAddress ?: "",
//            message = "message",
//            isPriorityContact = true
//        )
//
//        database.contactsDao().update(contactDtoToRoomItemMapper.map(updatedContact))
//
//        val result = database.contactsDao().getContactById(dbContact.id)
//
//        assertThat(result.id, `is`(dbContact.id))
//       // assertThat(result.contactName, `is`("Updated Contact"))
//        assertThat(result.phoneNumber, `is`("+375291112233"))
//        assertThat(result.isPriorityContact, `is`(true))
//
//
//    }

    @After
    fun onFinish() {
        database.close()

        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}