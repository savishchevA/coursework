package io.bsu.mmf.helpme.common.utils

import android.content.SharedPreferences

private const val COMPLETE_REGISTRATION = "COMPLETE_REGISTRATION"
private const val SEND_ALL_CONTACTS = "SEND_ALL_CONTACTS"
private const val ALARM_TIME = "ALARM_TIME"
private const val STAY_TIME = "v"

fun SharedPreferences.getRegistrationStatus(): Boolean = getBoolean(COMPLETE_REGISTRATION, false)

fun SharedPreferences.setRegistrationStatus(isCompleteRegistration: Boolean) {
    edit {  putBoolean(COMPLETE_REGISTRATION, isCompleteRegistration) }
}
fun SharedPreferences.isSendAllContacts(): Boolean = getBoolean(SEND_ALL_CONTACTS, false)

fun SharedPreferences.setSendAllContacts(sendAllContacts: Boolean) {
    edit {  putBoolean(SEND_ALL_CONTACTS, sendAllContacts) }
}

fun SharedPreferences.getStayTime(): String = getString(STAY_TIME, "10").orEmpty()

fun SharedPreferences.setStayTime(stayTime: String) {
    edit {
        putString(STAY_TIME, stayTime)
    }
}

fun SharedPreferences.getAlarmTime(): String = getString(ALARM_TIME, "10").orEmpty()

fun SharedPreferences.setAlarmTime(alarmTime: String) {
    edit {
        putString(ALARM_TIME, alarmTime)
    }
}

inline fun SharedPreferences.edit(
        commit: Boolean = false,
        action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}
