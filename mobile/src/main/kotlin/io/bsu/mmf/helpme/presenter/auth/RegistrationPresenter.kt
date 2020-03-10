package io.bsu.mmf.helpme.presenter.auth

import io.bsu.mmf.helpme.data.usecase.auth.CreateAccountUseCase
import io.bsu.mmf.helpme.data.usecase.sharedPreference.SetRegistrationStatusUseCase
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.view.auth.AuthView
import io.bsu.mmf.helpme.view.auth.RegistrationView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val setRegistrationStatusUseCase: SetRegistrationStatusUseCase
) : BasePresenter<RegistrationView>() {

    fun createAccount(account: Account) {
        localRepositoryScope.launch {
            createAccountUseCase(account).fold(
                    onSuccess = {
                        setRegistrationStatusUseCase(true)
                        viewState.successAccountCreation()
                    },
                    onFailure = {

                    }
            )
        }

    }

}