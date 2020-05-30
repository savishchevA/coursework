package io.bsu.mmf.helpme.common.repository.train

import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.flow.Flow

interface TrainRepository {

    suspend fun getAllTrains(): Flow<List<TrainItem>>

    suspend fun saveTrain(trainItem: TrainItem)

    suspend fun deleteTrain(trainItem: TrainItem)

}