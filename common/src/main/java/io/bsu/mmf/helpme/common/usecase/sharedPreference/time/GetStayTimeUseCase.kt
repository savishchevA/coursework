package io.bsu.mmf.helpme.common.usecase.sharedPreference.time

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository

class GetStayTimeUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(): String {
        return sharedPreferenceRepository.getStayTime()
    }

}