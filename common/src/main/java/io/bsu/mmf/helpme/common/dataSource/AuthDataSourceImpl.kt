package io.bsu.mmf.helpme.common.dataSource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import io.bsu.mmf.helpme.common.dataSource.auth.AuthDataSource
import io.bsu.mmf.helpme.common.mappers.auth.AuthResponseToDtoMapper
import io.bsu.mmf.helpme.data.ResultNetwork
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.data.auth.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class AuthDataSourceImpl (
        private val authResponseToDtoMapper: AuthResponseToDtoMapper
) : AuthDataSource {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun createAccount(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO) {
            val result = auth.createUserWithEmailAndPassword(account.email, account.password)

            try {
                Tasks.await(result)
                if (result.isSuccessful) {
                    ResultNetwork.Success(
                        authResponseToDtoMapper.map(result.result?.user)!!
                    )
                } else {
                    ResultNetwork.Error.OtherError(result.exception?.message ?: "")
                }
            } catch (e: Exception) {
                Timber.e(e.message)
                io.bsu.mmf.helpme.data.ResultNetwork.Error.OtherError(e.message ?: "")
            }


        }

    }


    override suspend fun loginWithEmail(account: Account): ResultNetwork<AuthData> {
        return withContext(Dispatchers.IO) {
            val result = auth.signInWithEmailAndPassword(account.email, account.password)

            try {
                Tasks.await(result)
                if (result.isSuccessful) {
                    ResultNetwork.Success(
                        authResponseToDtoMapper.map(result.result?.user)!!
                    )
                } else {
                    ResultNetwork.Error.OtherError("dasdas")
                }
            } catch (e: Exception) {
                Timber.e(e.message)
                ResultNetwork.Error.OtherError(e.message ?: "")
            }


        }
    }

    override suspend fun checkUserLogin(): Boolean {
        return auth.currentUser?.email == null
    }

    override suspend fun resetPassword(email: String): io.bsu.mmf.helpme.data.ResultNetwork<Unit> {
      return  withContext(Dispatchers.IO) {
            val result = auth.sendPasswordResetEmail(email)

          try {
              Tasks.await(result)
              if (result.isSuccessful) {
                  io.bsu.mmf.helpme.data.ResultNetwork.Success(Unit)
              } else {
                  io.bsu.mmf.helpme.data.ResultNetwork.Error.OtherError(result.exception?.message ?: "")
              }
          } catch (e: Exception) {
              Timber.e(e.message)
              io.bsu.mmf.helpme.data.ResultNetwork.Error.OtherError(e.message ?: "")
          }


        }


    }
}