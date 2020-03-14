package io.bsu.mmf.helpme.common.usecase.auth

import io.bsu.mmf.helpme.common.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CheckUserLoginUseCase (
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {
            authRepository.checkUserLogin()
        }
    }

}