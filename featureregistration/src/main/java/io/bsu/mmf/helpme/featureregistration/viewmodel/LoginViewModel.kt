package io.bsu.mmf.helpme.featureregistration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.BaseViewModel
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.auth.LoginWithEmailUseCase
import io.bsu.mmf.helpme.common.usecase.profile.CreateProfileUseCase
import io.bsu.mmf.helpme.common.usecase.sharedPreference.SetRegistrationStatusUseCase
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.data.auth.AuthData
import io.bsu.mmf.helpme.data.entity.local.Profile
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val setRegistrationStatusUseCase: SetRegistrationStatusUseCase,
    private val createProfileUseCase: CreateProfileUseCase
) : BaseViewModel() {

    private val _successLogin = MutableLiveData<AuthData>()
    val successLogin: LiveData<AuthData>
        get() = _successLogin


    fun login(account: Account) {
        execute({ loginWithEmailUseCase(account) }, _successLogin) {
            viewModelScope.launch {
                createProfileUseCase(
                    Profile(
                        name = it.name
                    )
                )
            }
            setRegistrationStatusUseCase.invoke(true)
        }
    }
}