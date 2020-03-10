package io.bsu.mmf.helpme.domain.usecase

import io.bsu.mmf.helpme.domain.repository.LocalRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CheckHasConfigUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(): Boolean {
        val hasConfig = localRepository.hasConfig()
        Timber.v("User has config: %s", hasConfig)
        return hasConfig
    }
}
