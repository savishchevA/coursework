package io.bsu.mmf.helpme.common.usecase.profile

import io.bsu.mmf.helpme.common.repository.profile.ProfileRepository
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateProfileUseCase (
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(profile: Profile) {
        withContext(Dispatchers.IO) {
            profileRepository.insertProfile(profile)
        }
    }

}