package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.repository.LocalRepository





class SaveMessageUseCase (private val localRepository: LocalRepository) {
    fun execute(message: String) = localRepository.saveMessage(message)
}
