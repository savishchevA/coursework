package io.bsu.mmf.helpme.data.usecase.auth

import io.bsu.mmf.helpme.data.repository.auth.AuthRepository
import io.bsu.mmf.helpme.domain.ResultNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String): ResultNetwork<Unit> {
        return withContext(Dispatchers.IO) {
            authRepository.resetPassword(email)
        }
    }

}