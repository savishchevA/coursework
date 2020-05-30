package io.bsu.mmf.helpme.common.dataSource.profile

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.dao.ProfileDao
import io.bsu.mmf.helpme.common.database.db.entity.ProfileRoomItem
import io.bsu.mmf.helpme.common.mappers.profile.ProfileDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.profile.ProfileRoomItemToDtoMapper
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileDataSourceImpl (
    private val profileDao: ProfileDao,
    private val profileRoomItemToDtoMapper: ProfileRoomItemToDtoMapper,
    private val profileDtoToRoomItemMapper: ProfileDtoToRoomItemMapper

): ProfileDataSource, BaseLocalDataSourceImpl<Profile, ProfileRoomItem>(
    profileDao, profileDtoToRoomItemMapper
) {

    override suspend fun getProfile(): Flow<Profile> {
        return profileDao.getProfile().map {
            profileRoomItemToDtoMapper.map(it)
        }
    }

    override suspend fun updateTrainDistance(trainDistance: String) {
        profileDao.updateTrainDistance(trainDistance)
    }

    override suspend fun updateTrainTime(trainTime: String) {
        profileDao.updateTrainTime(trainTime)
    }

    override suspend fun updateCommonMessage(commonMessage: String) {
        profileDao.updateCommonMessage(commonMessage)
    }

    override suspend fun updateStayTime(stayTime: String) {
        profileDao.updateStayTime(stayTime)
    }

    override suspend fun updateAlarmTime(alarmTime: String) {
        profileDao.updateAlarmTime(alarmTime)
    }
}