package io.bsu.mmf.helpme.common.dataSource.train

import io.bsu.mmf.helpme.common.dataSource.base.BaseLocalDataSource
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.flow.Flow

interface TrainDataSource : BaseLocalDataSource<TrainItem> {

    suspend fun getAllTrains(): Flow<List<TrainItem>>

}