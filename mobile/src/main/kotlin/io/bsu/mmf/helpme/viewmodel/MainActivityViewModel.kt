package io.bsu.mmf.helpme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.common.usecase.auth.CheckUserLoginUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.GetRegistrationStatusUseCase
import io.bsu.mmf.helpme.utils.Event
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val checkUserLoginUseCase: CheckUserLoginUseCase,
    private val getRegistrationStatusUseCase: GetRegistrationStatusUseCase
) : ViewModel() {

    private val _registrationStatus = MutableLiveData<Boolean>()
    val registrationStatus: LiveData<Boolean>
        get() = _registrationStatus

    private val _checkUserLogin = MutableLiveData<Boolean>()
    val checkUserLogin: LiveData<Boolean>
        get() = _checkUserLogin

    init {
        checkRegistrationStatus()
        checkUserLoginStatus()

    }

    private fun checkRegistrationStatus() {
        viewModelScope.launch {
            _registrationStatus.value = getRegistrationStatusUseCase()
        }
    }

    private fun checkUserLoginStatus() {
        viewModelScope.launch {
            _checkUserLogin.value = checkUserLoginUseCase()
        }
    }
}