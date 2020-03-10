package io.bsu.mmf.helpme.data.dataSource

import io.bsu.mmf.helpme.data.dataSource.base.BaseLocalDataSource
import io.bsu.mmf.helpme.domain.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource :
    BaseLocalDataSource<Contact> {

    suspend fun getAll(): Flow<List<Contact>>

}