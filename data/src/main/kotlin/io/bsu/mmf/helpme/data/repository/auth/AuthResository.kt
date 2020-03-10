package io.bsu.mmf.helpme.data.repository.auth

import io.bsu.mmf.helpme.data.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.domain.auth.AuthData

interface AuthRepository {
    suspend fun createAccount(account: Account): ResultNetwork<AuthData>
    suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData>
    suspend fun checkUserLogin(): Boolean
    suspend fun resetPassword(email: String): ResultNetwork<Unit>
}

class AuthRepositoryImpl (
        private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun createAccount(account: Account): ResultNetwork<AuthData> {
        return authDataSource.createAccount(account)
    }

    override suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData> {
        return authDataSource.loginWithEmail(account)
    }

    override suspend fun checkUserLogin(): Boolean {
        return authDataSource.checkUserLogin()
    }

    override suspend fun resetPassword(email: String): ResultNetwork<Unit> {
        return authDataSource.resetPassword(email)
    }
}