package io.bsu.mmf.helpme.common.dataSource.profile

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSource
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource : BaseLocalDataSource<Profile> {

    suspend fun getProfile(): Flow<Profile>
    suspend fun updateTrainDistance(trainDistance: String)
    suspend fun updateTrainTime(trainTime: String)

    suspend fun updateCommonMessage(commonMessage: String)
    suspend fun updateStayTime(stayTime: String)
    suspend fun updateAlarmTime(alarmTime: String)

}