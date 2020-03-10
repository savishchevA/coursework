package io.bsu.mmf.helpme.data.usecase.sharedPreference

import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository


class SetRegistrationStatusUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(isCompleteRegistration: Boolean) {
        sharedPreferenceRepository.setRegistrationStatus(isCompleteRegistration)
    }

}