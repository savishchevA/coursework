package io.bsu.mmf.helpme.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.bsu.mmf.helpme.data.usecase.auth.ResetPasswordUseCase
import io.bsu.mmf.helpme.utils.Event
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