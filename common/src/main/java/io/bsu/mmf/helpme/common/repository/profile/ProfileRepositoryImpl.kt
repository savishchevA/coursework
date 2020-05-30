package io.bsu.mmf.helpme.common.repository.profile

import io.bsu.mmf.helpme.common.dataSource.profile.ProfileDataSource
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl (
    private val profileDataSource: ProfileDataSource
): ProfileRepository {

    override suspend fun getProfile(): Flow<Profile> {
        return profileDataSource.getProfile()
    }

    override suspend fun updateTrainDistance(trainDistance: String) {
        profileDataSource.updateTrainDistance(trainDistance)
    }

    override suspend fun updateTrainTime(trainTime: String) {
        profileDataSource.updateTrainTime(trainTime)
    }

    override suspend fun insertProfile(profile: Profile) {
        profileDataSource.insert(profile)
    }

    override suspend fun updateCommonMessage(commonMessage: String) {
        profileDataSource.updateCommonMessage(commonMessage)
    }

    override suspend fun updateStayTime(stayTime: String) {
        profileDataSource.updateStayTime(stayTime)
    }

    override suspend fun updateAlarmTime(alarmTime: String) {
        profileDataSource.updateAlarmTime(alarmTime)
    }
}