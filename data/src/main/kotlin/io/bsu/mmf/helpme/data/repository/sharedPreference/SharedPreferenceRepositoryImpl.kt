package io.bsu.mmf.helpme.data.repository.sharedPreference

import io.bsu.mmf.helpme.data.dataSource.sharedPreference.SharedPreferenceDataSource
import javax.inject.Inject

class SharedPreferenceRepositoryImpl @Inject constructor(
    private val sharedPreferenceDataSource: SharedPreferenceDataSource
) : SharedPreferenceRepository {

    override fun getRegistrationStatus(): Boolean {
        return sharedPreferenceDataSource.getRegistrationStatus()
    }

    override fun setRegistrationStatus(isCompleteRegistration: Boolean) {
        sharedPreferenceDataSource.setRegistrationStatus(isCompleteRegistration)
    }
}