package io.bsu.mmf.helpme.common.database.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.bsu.mmf.helpme.common.database.db.entity.ProfileRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao : BaseDao<ProfileRoomItem> {

    @Query("SELECT * FROM profile")
    fun getProfile(): Flow<ProfileRoomItem>

    @Query("UPDATE profile SET trainDistance = :trainDistance")
    fun updateTrainDistance(trainDistance: String)

    @Query("UPDATE profile SET trainTime = :trainTime")
    fun updateTrainTime(trainTime: String)

}