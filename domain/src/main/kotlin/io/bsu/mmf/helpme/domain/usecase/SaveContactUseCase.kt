package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.entity.SosContact
import io.bsu.mmf.helpme.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SaveContactUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(sosContact: SosContact) = localRepository.saveContact(sosContact)
}