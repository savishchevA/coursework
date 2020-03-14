package io.bsu.mmf.helpme.common.usecase.auth

import io.bsu.mmf.helpme.common.repository.auth.AuthRepository
import io.bsu.mmf.helpme.data.ResultNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ResetPasswordUseCase (
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String): ResultNetwork<Unit> {
        return withContext(Dispatchers.IO) {
            authRepository.resetPassword(email)
        }
    }

}