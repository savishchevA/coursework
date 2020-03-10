package io.bsu.mmf.helpme.data.usecase

import io.bsu.mmf.helpme.data.repository.FirebaseContactRepository
import io.bsu.mmf.helpme.domain.entity.local.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveFBContactUseCase @Inject constructor(
        private val firebaseContactRepository: FirebaseContactRepository
) {

    suspend operator fun invoke(contact: Contact) {
        withContext(Dispatchers.IO) {
            firebaseContactRepository.saveContact(contact)
        }
    }

}