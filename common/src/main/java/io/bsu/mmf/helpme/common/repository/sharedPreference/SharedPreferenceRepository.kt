package io.bsu.mmf.helpme.common.repository.sharedPreference

interface SharedPreferenceRepository {

    fun getRegistrationStatus(): Boolean
    fun setRegistrationStatus(isCompleteRegistration: Boolean)


}