package io.bsu.mmf.helpme.common.database.db.entity

import androidx.room.*

@Entity(tableName = "contacts")
class ContactRoomItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "contactName")
    val contactName: String,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,

    @ColumnInfo(name = "address")
    val contactAddress: String? = null,

    @ColumnInfo(name = "longitude")
    val longitude: Double? = null,

    @ColumnInfo(name = "latitude")
    val latitude: Double? = null,

    @ColumnInfo(name = "priorityContact")
    val isPriorityContact: Boolean = false,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray? = null

)