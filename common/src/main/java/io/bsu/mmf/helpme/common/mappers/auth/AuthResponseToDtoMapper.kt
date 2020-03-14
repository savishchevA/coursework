package io.bsu.mmf.helpme.common.mappers.auth

import com.google.firebase.auth.FirebaseUser
import io.bsu.mmf.helpme.common.mappers.Mapper
import io.bsu.mmf.helpme.data.auth.AuthData


class AuthResponseToDtoMapper (

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