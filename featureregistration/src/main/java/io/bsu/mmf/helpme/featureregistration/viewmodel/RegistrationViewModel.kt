package io.bsu.mmf.helpme.featureregistration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.auth.CreateAccountUseCase
import io.bsu.mmf.helpme.common.usecase.profile.CreateProfileUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.SetRegistrationStatusUseCase
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val createAccountUseCase: CreateAccountUseCase,
    private val setRegistrationStatusUseCase: SetRegistrationStatusUseCase,
    private val createProfileUseCase: CreateProfileUseCase
) : ViewModel() {

    private val _successAccountCreation = MutableLiveData<Event<Unit>>()
    val successAccountCreation: LiveData<Event<Unit>>
        get() = _successAccountCreation

    fun createAccount(account: Account) {
        viewModelScope.launch {
            createAccountUseCase(account).fold(
                onSuccess = {
                    setRegistrationStatusUseCase(true)
                    createProfileUseCase(
                        Profile(
                            name = it.name
                        )
                    )
                    _successAccountCreation.value = Event(Unit)
                }
            )
        }
    }

}