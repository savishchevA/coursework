package io.bsu.mmf.helpme.common.dataSource.sharedPreference

interface SharedPreferenceDataSource {

    fun getRegistrationStatus(): Boolean
    fun setRegistrationStatus(isCompleteRegistration: Boolean)

    fun isSendAllContacts(): Boolean
    fun setSendAllContacts(isSendAllContacts: Boolean)

    fun getAlarmTime(): String
    fun setAlarmTime(alarmTime: String)

    fun getStayTime(): String
    fun setStayTime(stayTime: String)

}