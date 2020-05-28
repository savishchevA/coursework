package io.bsu.mmf.helpme.common.usecase.train

import io.bsu.mmf.helpme.common.repository.train.TrainRepository
import io.bsu.mmf.helpme.data.train.TrainItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveTrainUseCase (
    private val trainRepository: TrainRepository
) {

    suspend operator fun invoke(trainItem: TrainItem) {
        withContext(Dispatchers.IO) {
            trainRepository.saveTrain(trainItem)
        }
    }

}