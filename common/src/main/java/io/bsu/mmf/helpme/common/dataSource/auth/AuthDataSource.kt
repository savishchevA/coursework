package io.bsu.mmf.helpme.common.dataSource.auth

import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.data.auth.AuthData

interface AuthDataSource {

    suspend fun createAccount(account: Account): ResultNetwork<AuthData>
    suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData>
    suspend fun checkUserLogin(): Boolean
    suspend fun resetPassword(email: String): ResultNetwork<Unit>

}