package io.bsu.mmf.helpme.common.usecase.sharedPreference.time

import io.bsu.mmf.helpme.common.repository.sharedPreference.SharedPreferenceRepository


class SetAlarmTimeUseCase (
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    operator fun invoke(alarmTime: String) {
        sharedPreferenceRepository.setAlarmTime(alarmTime)
    }

}