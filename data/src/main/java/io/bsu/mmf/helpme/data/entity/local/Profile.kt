package io.bsu.mmf.helpme.data.entity.local

private const val DEFAULT_STAY_TIME = "10"
private const val DEFAULT_ALARM_TIME = "10"

data class Profile(
    val name: String,
    val trainDistance: String = "",
    val trainTime: String = "",
    val stayTime: String = DEFAULT_STAY_TIME,
    val alarmTime: String = DEFAULT_ALARM_TIME,
    val commonMessage: String = ""
)