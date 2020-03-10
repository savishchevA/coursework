package io.bsu.mmf.helpme.presenter.auth

import io.bsu.mmf.helpme.data.usecase.SaveFBContactUseCase
import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.view.auth.RegistrationSecondView
import io.bsu.mmf.helpme.view.auth.RegistrationView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class RegistrationSecondPresenter @Inject constructor(
    private val saveFBContactUseCase: SaveFBContactUseCase
) : BasePresenter<RegistrationSecondView>() {

    fun saveContact(contact: Contact) {
        localRepositoryScope.launch {
            saveFBContactUseCase(contact)
            viewState.navigateToMainScreen()
        }
    }

}