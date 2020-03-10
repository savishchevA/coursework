package io.bsu.mmf.helpme.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.data.usecase.auth.CreateAccountUseCase
import io.bsu.mmf.helpme.data.usecase.sharedPreference.SetRegistrationStatusUseCase
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.utils.Event
import kotlinx.coroutines.launch

class RegistrationViewModel(
        private val createAccountUseCase: CreateAccountUseCase,
        private val setRegistrationStatusUseCase: SetRegistrationStatusUseCase
) : ViewModel() {

    private val _successAccountCreation = MutableLiveData<Event<Unit>>()
    val successAccountCreation: LiveData<Event<Unit>>
        get() = _successAccountCreation

    fun createAccount(account: Account) {
        viewModelScope.launch {
            createAccountUseCase(account).fold(
                    onSuccess = {
                        setRegistrationStatusUseCase(true)
                        _successAccountCreation.value = Event(Unit)
                    }
            )
        }
    }

}