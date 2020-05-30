package io.bsu.mmf.helpme.common.dataSource

import androidx.paging.DataSource
import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSource
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsDataSource :
    BaseLocalDataSource<Contact> {

    suspend fun getAll(): Flow<List<Contact>>

    suspend fun getPriorityContact(): Contact

    fun getAllSource(): DataSource.Factory<Int, Contact>

    suspend fun deleteContactById(contactId: Int)
    suspend fun getContactById(contactId: Int): Contact

    suspend fun updatePrimaryContact(contactId: Int)
    suspend fun getFlowPrimaryContact(): Flow<Contact>

    fun getAllContactsList(): List<Contact>

    suspend fun getContactsCount(): Int
}