package io.bsu.mmf.helpme.data.repository.sharedPreference

interface SharedPreferenceRepository {

    fun getRegistrationStatus(): Boolean
    fun setRegistrationStatus(isCompleteRegistration: Boolean)


}