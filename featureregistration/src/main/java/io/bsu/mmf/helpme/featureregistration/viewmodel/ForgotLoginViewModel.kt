package io.bsu.mmf.helpme.featureregistration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.baseAndroid.utils.Event
import io.bsu.mmf.helpme.common.usecase.auth.ResetPasswordUseCase
import kotlinx.coroutines.launch

class ForgotLoginViewModel (
        private val resetPasswordUseCase: ResetPasswordUseCase
): ViewModel() {

    private val _successPasswordReset = MutableLiveData<Event<Unit>>()
    val successPasswordReset: LiveData<Event<Unit>>
    get() = _successPasswordReset

    fun resetPassword(email: String) {
        viewModelScope.launch {
            resetPasswordUseCase(email).fold(
                    onSuccess = {
                        _successPasswordReset.value = Event(Unit)
                    }
            )
        }
    }

}