package io.bsu.mmf.helpme.data.dataSource.sharedPreference

interface SharedPreferenceDataSource {

    fun getRegistrationStatus(): Boolean
    fun setRegistrationStatus(isCompleteRegistration: Boolean)

}