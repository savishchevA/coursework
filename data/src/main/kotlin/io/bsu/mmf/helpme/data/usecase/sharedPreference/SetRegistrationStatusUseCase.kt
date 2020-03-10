package io.bsu.mmf.helpme.data.usecase.sharedPreference

import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository
import javax.inject.Inject

class SetRegistrationStatusUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(isCompleteRegistration: Boolean) {
        sharedPreferenceRepository.setRegistrationStatus(isCompleteRegistration)
    }

}