package io.bsu.mmf.helpme.data.usecase.sharedPreference

import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository


class GetRegistrationStatusUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Boolean {
        return sharedPreferenceRepository.getRegistrationStatus()
    }

}