package io.bsu.mmf.helpme.data

//import com.google.firebase.database.IgnoreExtraProperties


data class ContactFb(
        var username: String? = "",
        var phone: String? = "",
        var address: String? = "",
        var message: String? = "",
        var isPriorityContact: Boolean? = false
)