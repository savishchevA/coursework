package io.bsu.mmf.helpme.data.dataSource.sharedPreference

import android.content.SharedPreferences
import io.bsu.mmf.helpme.data.utils.getRegistrationStatus
import io.bsu.mmf.helpme.data.utils.setRegistrationStatus
import javax.inject.Inject

class SharedPreferenceDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferenceDataSource {

    override fun getRegistrationStatus(): Boolean {
        return sharedPreferences.getRegistrationStatus()
    }

    override fun setRegistrationStatus(isCompleteRegistration: Boolean) {
        sharedPreferences.setRegistrationStatus(isCompleteRegistration)
    }
}