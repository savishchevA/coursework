package io.bsu.mmf.helpme.common.usecase.sharedPreference

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository


class SetRegistrationStatusUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(isCompleteRegistration: Boolean) {
        sharedPreferenceRepository.setRegistrationStatus(isCompleteRegistration)
    }

}