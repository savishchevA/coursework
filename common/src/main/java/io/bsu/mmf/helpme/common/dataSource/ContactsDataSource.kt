package io.bsu.mmf.helpme.common.dataSource

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSource
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource :
    BaseLocalDataSource<Contact> {

    suspend fun getAll(): Flow<List<Contact>>

}