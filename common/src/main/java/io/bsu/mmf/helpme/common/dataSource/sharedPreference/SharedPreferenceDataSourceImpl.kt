package io.bsu.mmf.helpme.common.dataSource.sharedPreference

import android.content.SharedPreferences
import io.bsu.mmf.helpme.common.utils.*


class SharedPreferenceDataSourceImpl(
    private val sharedPreferences: SharedPreferences
) : SharedPreferenceDataSource {

    override fun getRegistrationStatus(): Boolean {
        return sharedPreferences.getRegistrationStatus()
    }

    override fun setRegistrationStatus(isCompleteRegistration: Boolean) {
        sharedPreferences.setRegistrationStatus(isCompleteRegistration)
    }

    override fun isSendAllContacts(): Boolean {
        return sharedPreferences.isSendAllContacts()
    }

    override fun setSendAllContacts(isSendAllContacts: Boolean) {
        sharedPreferences.setSendAllContacts(isSendAllContacts)
    }

    override fun getAlarmTime(): String {
        return sharedPreferences.getAlarmTime()
    }

    override fun setAlarmTime(alarmTime: String) {
        sharedPreferences.setAlarmTime(alarmTime)
    }

    override fun getStayTime(): String {
        return sharedPreferences.getStayTime()
    }

    override fun setStayTime(stayTime: String) {
        sharedPreferences.setStayTime(stayTime)
    }
}