package io.bsu.mmf.helpme.data.usecase.auth

import io.bsu.mmf.helpme.data.repository.auth.AuthRepository
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.domain.auth.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
        private val authRepository: AuthRepository
) {

    suspend operator fun invoke(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO){
            authRepository.createAccount(account)
        }
    }

}