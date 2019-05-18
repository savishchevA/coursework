package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchTimeUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun getTime() = localRepository.getTime()
    fun setTime(time: Int) = localRepository.saveTime(time)
}
