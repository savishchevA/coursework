package io.bsu.mmf.helpme.common.usecase

import io.bsu.mmf.helpme.common.repository.FirebaseContactRepository
import io.bsu.mmf.helpme.data.entity.local.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFBContactUseCase (
    private val firebaseContactRepository: FirebaseContactRepository
) {

    suspend operator fun invoke(): Contact {
        return withContext(Dispatchers.IO) {
            firebaseContactRepository.getContact()
        }
    }

}