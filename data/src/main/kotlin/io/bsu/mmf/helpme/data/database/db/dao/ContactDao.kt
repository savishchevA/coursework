package io.bsu.mmf.helpme.data.database.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.bsu.mmf.helpme.data.database.db.entity.ContactRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao : BaseDao<ContactRoomItem> {

    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<ContactRoomItem>>

}