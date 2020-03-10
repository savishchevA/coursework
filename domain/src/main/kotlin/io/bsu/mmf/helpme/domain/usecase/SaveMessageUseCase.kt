package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SaveMessageUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(message: String) = localRepository.saveMessage(message)
}
