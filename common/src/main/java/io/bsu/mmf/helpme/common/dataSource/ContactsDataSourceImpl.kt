package io.bsu.mmf.helpme.common.dataSource

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.dao.ContactDao
import io.bsu.mmf.helpme.common.database.db.entity.ContactRoomItem
import io.bsu.mmf.helpme.common.mappers.contacts.ContactDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.contacts.ContactRoomItemToDtoMapper
import io.bsu.mmf.helpme.common.mappers.toListMapper
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ContactsDataSourceImpl (
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
}