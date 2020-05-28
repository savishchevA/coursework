package io.bsu.mmf.helpme.common.usecase.profile

import io.bsu.mmf.helpme.common.repository.profile.ProfileRepository
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetProfileUseCase (
    private val profileRepository: ProfileRepository 
) {
    suspend operator fun invoke(): Flow<Profile> {
        return withContext(Dispatchers.IO) {
            profileRepository.getProfile()   
        }
    }
}