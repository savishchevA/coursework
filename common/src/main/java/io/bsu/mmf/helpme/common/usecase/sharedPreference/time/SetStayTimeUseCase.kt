package io.bsu.mmf.helpme.common.usecase.sharedPreference.time

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository


class SetStayTimeUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(stayTime: String) {
        sharedPreferenceRepository.setStayTime(stayTime)
    }

}