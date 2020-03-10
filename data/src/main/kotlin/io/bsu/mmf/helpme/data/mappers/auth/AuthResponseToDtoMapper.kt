package io.bsu.mmf.helpme.data.mappers.auth

import com.google.firebase.auth.FirebaseUser
import io.bsu.mmf.helpme.data.mappers.Mapper
import io.bsu.mmf.helpme.domain.auth.AuthData
import javax.inject.Inject

class AuthResponseToDtoMapper @Inject constructor(

): Mapper<FirebaseUser?, AuthData?> {
    override fun map(from: FirebaseUser?): AuthData? {
        return from?.let {
            AuthData(
                    email = it.email ?: "",
                    name = it.displayName ?: "",
                    phone = it.phoneNumber ?: ""
            )
        }
    }
}