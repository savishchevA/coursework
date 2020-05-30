package io.bsu.mmf.helpme.common.database.db.dao

import androidx.room.Dao
import androidx.room.Query
import io.bsu.mmf.helpme.common.database.db.entity.TrainRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainDao : BaseDao<TrainRoomItem> {


    @Query("SELECT * FROM train ORDER BY id DESC")
    fun getAll(): Flow<List<TrainRoomItem>>


}