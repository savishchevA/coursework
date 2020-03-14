package io.bsu.mmf.helpme.common.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ContactFb(
        var username: String? = "",
        var phone: String? = "",
        var address: String? = "",
        var message: String? = "",
        var isPriorityContact: Boolean? = false
)