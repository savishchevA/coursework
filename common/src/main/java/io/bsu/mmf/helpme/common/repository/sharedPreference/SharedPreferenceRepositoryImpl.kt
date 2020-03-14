package io.bsu.mmf.helpme.common.repository.sharedPreference

import io.bsu.mmf.helpme.common.dataSource.sharedPreference.SharedPreferenceDataSource


class SharedPreferenceRepositoryImpl (
    private val sharedPreferenceDataSource: SharedPreferenceDataSource
) : SharedPreferenceRepository {

    override fun getRegistrationStatus(): Boolean {
        return sharedPreferenceDataSource.getRegistrationStatus()
    }

    override fun setRegistrationStatus(isCompleteRegistration: Boolean) {
        sharedPreferenceDataSource.setRegistrationStatus(isCompleteRegistration)
    }
}