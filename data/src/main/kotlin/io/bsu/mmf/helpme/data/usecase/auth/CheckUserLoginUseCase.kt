package io.bsu.mmf.helpme.data.usecase.auth

import io.bsu.mmf.helpme.data.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckUserLoginUseCase @Inject constructor(
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Boolean {
        return withContext(Dispatchers.IO) {
            authRepository.checkUserLogin()
        }
    }

}