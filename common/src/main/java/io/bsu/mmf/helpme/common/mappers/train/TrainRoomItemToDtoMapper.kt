package io.bsu.mmf.helpme.common.mappers.train

import io.bsu.mmf.helpme.common.database.db.entity.TrainRoomItem
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.train.TrainItem

class TrainRoomItemToDtoMapper (
) : Mapper<TrainRoomItem, TrainItem> {
    override fun map(from: TrainRoomItem): TrainItem {
        return TrainItem(
            id = from.id,
            distance = from.trainDistance.orEmpty(),
            time = from.trainTime.orEmpty(),
            date = from.trainDate
        )
    }
}