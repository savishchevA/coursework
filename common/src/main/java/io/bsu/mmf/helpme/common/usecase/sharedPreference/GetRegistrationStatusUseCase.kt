package io.bsu.mmf.helpme.common.usecase.sharedPreference

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository


class GetRegistrationStatusUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Boolean {
        return sharedPreferenceRepository.getRegistrationStatus()
    }

}