package io.bsu.mmf.helpme.data.entity.local


data class Contact(
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val address: String,
    val message: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val contactImage: ByteArray? = null,
    val isPriorityContact: Boolean
)