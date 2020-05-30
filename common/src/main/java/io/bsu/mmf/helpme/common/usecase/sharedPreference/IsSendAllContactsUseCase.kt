package io.bsu.mmf.helpme.common.usecase.sharedPreference

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository

class IsSendAllContactsUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): Boolean {
        return sharedPreferenceRepository.isSendAllContacts()
    }

}