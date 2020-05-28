package io.bsu.mmf.helpme.common.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.bsu.mmf.helpme.common.database.db.dao.*
import io.bsu.mmf.helpme.common.database.db.entity.*

@Database(
    entities = [ContactRoomItem::class, ProfileRoomItem::class, TrainRoomItem::class],
    version = 1
)
    abstract class AppDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactDao
    abstract fun profileDao(): ProfileDao
    abstract fun trainDao(): TrainDao
}