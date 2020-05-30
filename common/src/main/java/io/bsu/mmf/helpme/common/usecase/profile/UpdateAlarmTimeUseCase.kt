package io.bsu.mmf.helpme.common.usecase.profile

import io.bsu.mmf.helpme.common.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateAlarmTimeUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(alarmTime: String) {
        withContext(Dispatchers.IO) {
            profileRepository.updateAlarmTime(alarmTime)
        }
    }

}