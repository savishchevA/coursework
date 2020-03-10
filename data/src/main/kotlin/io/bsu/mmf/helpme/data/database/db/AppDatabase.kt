package io.bsu.mmf.helpme.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.bsu.mmf.helpme.data.database.db.dao.ContactDao
import io.bsu.mmf.helpme.data.database.db.entity.ContactRoomItem

@Database(
    entities = [ContactRoomItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactDao
}