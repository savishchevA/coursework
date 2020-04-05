package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.common.usecase.sms.SendMessageForAllUseCase
import io.bsu.mmf.helpme.common.usecase.sms.SendSmsUseCase
import kotlinx.coroutines.launch

class SendMessageViewModel (
    private val sendSmsUseCase: SendSmsUseCase,
    private val sendMessageForAllUseCase: SendMessageForAllUseCase
) : ViewModel() {


    fun sendSms() {
        viewModelScope.launch {
            sendSmsUseCase()
        }
    }

    fun sendMessageForAllContacts() {
        viewModelScope.launch {
            sendMessageForAllUseCase()
        }
    }

}