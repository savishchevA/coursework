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
    suspend fun updateTrainDistance(trainDistance: String)

    @Query("UPDATE profile SET trainTime = :trainTime")
    suspend fun updateTrainTime(trainTime: String)

    @Query("UPDATE profile SET commonMessage = :commonMessage")
    suspend fun updateCommonMessage(commonMessage: String)

    @Query("UPDATE profile SET stayTime = :stayTime")
    suspend fun updateStayTime(stayTime: String)

    @Query("UPDATE profile SET alarmTime = :alarmTime")
    suspend fun updateAlarmTime(alarmTime: String)

}