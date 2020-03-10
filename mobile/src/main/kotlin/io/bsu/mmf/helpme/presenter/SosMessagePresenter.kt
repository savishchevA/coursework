package io.bsu.mmf.helpme.presenter

import io.bsu.mmf.helpme.domain.usecase.CheckHasConfigUseCase
import io.bsu.mmf.helpme.view.SosMessageView
import io.bsu.mmf.helpme.view.welcome.WelcomeView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SosMessagePresenter @Inject constructor(
): BasePresenter<SosMessageView>() {

}
