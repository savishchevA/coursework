package io.bsu.mmf.helpme.featuremain.viewmodel

import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.common.usecase.sms.SendMessageForAllUseCase
import kotlinx.coroutines.launch

class HelpViewModel(
    private val sendMessageForAllUseCase: SendMessageForAllUseCase
): BaseViewModel() {


    fun sendMessage() {
        viewModelScope.launch {
            sendMessageForAllUseCase()
        }
    }

}