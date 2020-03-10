package io.bsu.mmf.helpme.data.usecase.sharedPreference

import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository
import javax.inject.Inject

class GetRegistrationStatusUseCase @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Boolean {
        return sharedPreferenceRepository.getRegistrationStatus()
    }

}