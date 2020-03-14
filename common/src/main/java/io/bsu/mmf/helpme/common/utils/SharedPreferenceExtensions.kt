package io.bsu.mmf.helpme.common.utils

import android.content.SharedPreferences

private const val COMPLETE_REGISTRATION = "COMPLETE_REGISTRATION"

fun SharedPreferences.getRegistrationStatus(): Boolean = getBoolean(COMPLETE_REGISTRATION, false)

fun SharedPreferences.setRegistrationStatus(isCompleteRegistration: Boolean) {
    edit {  putBoolean(COMPLETE_REGISTRATION, isCompleteRegistration) }
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
