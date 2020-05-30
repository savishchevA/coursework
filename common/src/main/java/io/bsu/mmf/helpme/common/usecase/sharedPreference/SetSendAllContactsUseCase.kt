package io.bsu.mmf.helpme.common.usecase.sharedPreference

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository

class SetSendAllContactsUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(isSendAllContacts: Boolean) {
        sharedPreferenceRepository.setSendAllContacts(isSendAllContacts)
    }

}