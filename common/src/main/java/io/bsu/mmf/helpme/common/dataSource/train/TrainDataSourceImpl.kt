package io.bsu.mmf.helpme.common.dataSource.train

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSourceImpl
import io.bsu.mmf.helpme.common.database.db.dao.TrainDao
import io.bsu.mmf.helpme.common.database.db.entity.TrainRoomItem
import io.bsu.mmf.helpme.common.mappers.toListMapper
import io.bsu.mmf.helpme.common.mappers.train.TrainDtoToRoomItemMapper
import io.bsu.mmf.helpme.common.mappers.train.TrainRoomItemToDtoMapper
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrainDataSourceImpl(
    private val trainDao: TrainDao,
    private val trainDtoToRoomItemMapper: TrainDtoToRoomItemMapper,
    private val trainRoomItemToDtoMapper: TrainRoomItemToDtoMapper
) : TrainDataSource, BaseLocalDataSourceImpl<TrainItem, TrainRoomItem>(
    trainDao, trainDtoToRoomItemMapper
) {
    override suspend fun getAllTrains(): Flow<List<TrainItem>> {
        return trainDao.getAll().map {
            trainRoomItemToDtoMapper.toListMapper().map(it)
        }
    }
}