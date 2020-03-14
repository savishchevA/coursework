package io.bsu.mmf.helpme.common.usecase.auth

import io.bsu.mmf.helpme.common.repository.auth.AuthRepository
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.data.auth.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LoginWithEmailUseCase (
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO) {
            authRepository.loginWithEmail(account)
        }
    }


}