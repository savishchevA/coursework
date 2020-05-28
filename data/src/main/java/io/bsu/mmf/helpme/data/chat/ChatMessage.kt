package io.bsu.mmf.helpme.data.chat

data class ChatMessage(
    val user: String,
    val messageId: String,
    val message: String,
    val time: String
)