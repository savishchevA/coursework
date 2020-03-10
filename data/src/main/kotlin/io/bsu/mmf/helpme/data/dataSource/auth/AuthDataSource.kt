package io.bsu.mmf.helpme.data.dataSource.auth

import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.domain.auth.AuthData

interface AuthDataSource {

    suspend fun createAccount(account: Account): ResultNetwork<AuthData>
    suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData>
    suspend fun checkUserLogin(): Boolean
    suspend fun resetPassword(email: String): ResultNetwork<Unit>

}