package io.bsu.mmf.helpme.presenter.welcome

import io.bsu.mmf.helpme.domain.usecase.CheckHasConfigUseCase
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.view.welcome.WelcomeView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class WelcomeActivityPresenter @Inject constructor(
    private val checkHasConfig: CheckHasConfigUseCase
): BasePresenter<WelcomeView>() {

    fun checkConfig() {
        if (checkHasConfig.execute()) {
            viewState?.goToNextScreen()
        } else {
            viewState?.showConfigNeededMessage()
        }
    }
}
