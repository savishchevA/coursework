package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.entity.SosContact
import io.bsu.mmf.helpme.domain.repository.LocalRepository





class SaveContactUseCase (private val localRepository: LocalRepository) {
    fun execute(sosContact: SosContact) = localRepository.saveContact(sosContact)
}
