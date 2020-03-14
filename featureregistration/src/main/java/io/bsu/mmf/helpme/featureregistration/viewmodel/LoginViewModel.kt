package io.bsu.mmf.helpme.featureregistration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.auth.LoginWithEmailUseCase
import io.bsu.mmf.helpme.data.auth.Account
import kotlinx.coroutines.launch

class LoginViewModel (
        private val loginWithEmailUseCase: LoginWithEmailUseCase
): ViewModel() {

    private val _successLogin = MutableLiveData<Event<Unit>>()
    val successLogin: LiveData<Event<Unit>>
        get() = _successLogin


    fun login(account: Account) {
       viewModelScope.launch {
           loginWithEmailUseCase(account).fold(
                   onSuccess = {
                       _successLogin.value = Event(Unit)
                   }
           )
       }
    }
}