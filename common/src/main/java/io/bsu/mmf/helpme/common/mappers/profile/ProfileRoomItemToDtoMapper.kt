package io.bsu.mmf.helpme.common.mappers.profile

import io.bsu.mmf.helpme.common.database.db.entity.ProfileRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Profile


private const val DEFAULT_STAY_TIME = "10"
private const val DEFAULT_ALARM_TIME = "10"

class ProfileRoomItemToDtoMapper(
) : Mapper<ProfileRoomItem, Profile> {
    override fun map(from: ProfileRoomItem): Profile {
        return with(from) {
            Profile(
                name = name,
                trainDistance = trainDistance.orEmpty(),
                trainTime = trainTime.orEmpty(),
                alarmTime = alarmTime ?: DEFAULT_ALARM_TIME,
                commonMessage = commonMessage.orEmpty(),
                stayTime = stayTime ?: DEFAULT_STAY_TIME
            )
        }
    }
}