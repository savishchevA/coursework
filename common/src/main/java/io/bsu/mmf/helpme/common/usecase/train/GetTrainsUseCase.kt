package io.bsu.mmf.helpme.common.usecase.train

import io.bsu.mmf.helpme.common.repository.train.TrainRepository
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetTrainsUseCase(
    private val trainRepository: TrainRepository
) {

    suspend operator fun invoke(): Flow<List<TrainItem>> {
        return withContext(Dispatchers.IO) {
            trainRepository.getAllTrains()
        }
    }

}
