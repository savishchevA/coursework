package io.bsu.mmf.helpme.common.mappers.train

import io.bsu.mmf.helpme.common.database.db.entity.TrainRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.train.TrainItem

class TrainDtoToRoomItemMapper(
) : Mapper<TrainItem, TrainRoomItem> {
    override fun map(from: TrainItem): TrainRoomItem {
        return TrainRoomItem(
            trainDistance = from.distance,
            trainTime = from.time,
            trainDate = from.date
        )
    }
}