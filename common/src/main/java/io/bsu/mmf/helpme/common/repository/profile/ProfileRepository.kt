package io.bsu.mmf.helpme.common.repository.profile

import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile(): Flow<Profile>
    suspend fun updateTrainDistance(trainDistance: String)
    suspend fun updateTrainTime(trainTime: String)

    suspend fun insertProfile(profile: Profile)
}