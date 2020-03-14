package io.bsu.mmf.helpme.common.dataSource.sharedPreference

interface SharedPreferenceDataSource {

    fun getRegistrationStatus(): Boolean
    fun setRegistrationStatus(isCompleteRegistration: Boolean)

}