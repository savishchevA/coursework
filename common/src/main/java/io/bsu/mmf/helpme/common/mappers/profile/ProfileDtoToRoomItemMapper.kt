package io.bsu.mmf.helpme.common.mappers.profile

import io.bsu.mmf.helpme.common.database.db.entity.ProfileRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.entity.local.Profile

class ProfileDtoToRoomItemMapper (
) : Mapper<Profile, ProfileRoomItem> {
    override fun map(from: Profile): ProfileRoomItem {
        return ProfileRoomItem(
            name = from.name,
            trainTime = from.trainTime,
            trainDistance = from.trainDistance

        )
    }
}