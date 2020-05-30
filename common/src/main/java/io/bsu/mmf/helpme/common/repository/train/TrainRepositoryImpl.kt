package io.bsu.mmf.helpme.common.repository.train

import io.bsu.mmf.helpme.common.dataSource.train.TrainDataSource
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.flow.Flow

class TrainRepositoryImpl (
    private val trainDataSource: TrainDataSource
): TrainRepository {

    override suspend fun getAllTrains(): Flow<List<TrainItem>> {
        return trainDataSource.getAllTrains()
    }

    override suspend fun saveTrain(trainItem: TrainItem) {
        trainDataSource.insert(trainItem)
    }

    override suspend fun deleteTrain(trainItem: TrainItem) {
        trainDataSource.delete(trainItem)
    }
}