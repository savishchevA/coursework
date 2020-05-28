package io.bsu.mmf.helpme.common.database.db.entity

import androidx.room.*


@Entity(tableName = "train")
class TrainRoomItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "trainDate")
    val trainDate: String,

    @ColumnInfo(name = "trainDistance")
    val trainDistance: String? = null,

    @ColumnInfo(name = "trainTime")
    val trainTime: String? = null

)