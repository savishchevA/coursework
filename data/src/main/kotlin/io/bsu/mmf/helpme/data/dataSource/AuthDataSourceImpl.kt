package io.bsu.mmf.helpme.data.dataSource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import io.bsu.mmf.helpme.data.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.data.mappers.auth.AuthResponseToDtoMapper
import io.bsu.mmf.helpme.domain.ResultNetwork
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.domain.auth.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthDataSourceImpl (
        private val authResponseToDtoMapper: AuthResponseToDtoMapper
) : AuthDataSource {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun createAccount(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO) {
            val result = auth.createUserWithEmailAndPassword(account.email, account.password)
            Tasks.await(result)
            if (result.isSuccessful) {
                ResultNetwork.Success(
                        authResponseToDtoMapper.map(result.result?.user)!!
                )
            } else {
                ResultNetwork.Error.OtherError(result.exception?.message ?: "")
            }
        }

    }


    override suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO) {
            val result = auth.signInWithEmailAndPassword(account.email, account.password)
            Tasks.await(result)
            if (result.isSuccessful) {
                ResultNetwork.Success(
                        authResponseToDtoMapper.map(result.result?.user)!!
                )
            } else {
                ResultNetwork.Error.OtherError("dasdas")
            }
        }
    }

    override suspend fun checkUserLogin(): Boolean {
        return auth.currentUser?.email == null
    }

    override suspend fun resetPassword(email: String): ResultNetwork<Unit> {
      return  withContext(Dispatchers.IO) {
            val result = auth.sendPasswordResetEmail(email)
            Tasks.await(result)
            if (result.isSuccessful) {
                ResultNetwork.Success(Unit)
            } else {
                ResultNetwork.Error.OtherError(result.exception?.message ?: "")
            }
        }


    }
}