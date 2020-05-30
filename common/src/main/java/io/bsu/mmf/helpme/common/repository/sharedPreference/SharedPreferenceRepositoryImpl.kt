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

    override fun isSendAllContacts(): Boolean {
        return sharedPreferenceDataSource.isSendAllContacts()
    }

    override fun setSendAllContacts(isSendAllContacts: Boolean) {
        sharedPreferenceDataSource.setSendAllContacts(isSendAllContacts)
    }

    override fun getAlarmTime(): String {
        return sharedPreferenceDataSource.getAlarmTime()
    }

    override fun setAlarmTime(alarmTime: String) {
        sharedPreferenceDataSource.setAlarmTime(alarmTime)
    }

    override fun getStayTime(): String {
        return sharedPreferenceDataSource.getStayTime()
    }

    override fun setStayTime(stayTime: String) {
        sharedPreferenceDataSource.setStayTime(stayTime)
    }
}