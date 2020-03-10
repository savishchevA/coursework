package io.bsu.mmf.helpme.domain.entity.local


data class Contact(
    val name: String,
    val phoneNumber: String,
    val address: String,
    val message: String,
    val isPriorityContact: Boolean
)