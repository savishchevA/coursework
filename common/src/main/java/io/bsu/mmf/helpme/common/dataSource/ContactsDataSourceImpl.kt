package io.bsu.mmf.helpme.common.dataSource

import androidx.paging.DataSource
import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.dao.ContactDao
import io.bsu.mmf.helpme.common.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.common.mappers.contacts.ContactDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.toListMapper
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ContactsDataSourceImpl(
    private val contactDao: ContactDao,
    private val contactRoomItemToDtoMapper: ContactRoomItemToDtoMapper,
    private val contactDtoToRoomItemMapper: ContactDtoToRoomItemMapper
) : ContactsDataSource, BaseLocalDataSourceImpl<Contact, ContactRoomItem>(
    contactDao, contactDtoToRoomItemMapper
) {

    override suspend fun getAll(): Flow<List<Contact>> {
        return contactDao.getAll().map {
            contactRoomItemToDtoMapper.toListMapper().map(it)
        }
    }

    override suspend fun getPriorityContact(): Contact {
        return contactRoomItemToDtoMapper.map(
            contactDao.getPriorityContact(true)
        )
    }

    override fun getAllSource(): DataSource.Factory<Int, Contact> {
        return contactDao.getAllSource().map(contactRoomItemToDtoMapper::map)
    }

    override suspend fun deleteContactById(contactId: Int) {
        contactDao.deleteContactById(contactId)
    }

    override suspend fun getContactById(contactId: Int): Contact {
        return contactRoomItemToDtoMapper.map(
            contactDao.getContactById(contactId)
        )
    }

    override suspend fun updatePrimaryContact(contactId: Int) {
        contactDao.setPrimaryContact(contactId)
    }

    override suspend fun getFlowPrimaryContact(): Flow<Contact> {
        return contactDao.getFlowPrimaryContact(true).map {
            contactRoomItemToDtoMapper.map(it)
        }
    }

    override fun getAllContactsList(): List<Contact> {
        return contactRoomItemToDtoMapper.toListMapper().map(
            contactDao.getAllContactsList()
        )
    }

    override suspend fun getContactsCount(): Int {
        return contactDao.count()
    }
}