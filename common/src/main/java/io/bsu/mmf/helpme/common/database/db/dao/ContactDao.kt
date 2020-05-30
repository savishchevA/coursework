package io.bsu.mmf.helpme.common.database.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import io.bsu.mmf.helpme.common.database.db.entity.ContactRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao : BaseDao<ContactRoomItem> {

    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<ContactRoomItem>>

    @Query("SELECT * FROM contacts")
    fun getAllContactsList(): List<ContactRoomItem>

    @Query("SELECT * FROM contacts")
    fun getAllSource(): DataSource.Factory<Int, ContactRoomItem>

    @Query("SELECT * FROM contacts WHERE contactName = :name ")
    suspend fun getContactByName(name: String): ContactRoomItem

    @Query("SELECT * FROM contacts WHERE id = :id ")
    suspend fun getContactById(id: Int): ContactRoomItem


    @Query("SELECT * FROM contacts WHERE priorityContact = :isPriority")
    suspend fun getPriorityContact(isPriority: Boolean): ContactRoomItem

    @Query("DELETE FROM contacts WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Int)

    @Query("UPDATE contacts SET priorityContact = (id = :contactId)")
    suspend fun setPrimaryContact(contactId: Int)

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun count(): Int

    @Query("SELECT * FROM contacts WHERE priorityContact = :isPriority")
    fun getFlowPrimaryContact(isPriority: Boolean): Flow<ContactRoomItem>

}