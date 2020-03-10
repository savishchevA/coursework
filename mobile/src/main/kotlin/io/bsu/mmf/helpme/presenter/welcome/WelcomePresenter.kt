package io.bsu.mmf.helpme.presenter.welcome

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.welcome.WelcomeView


interface WelcomePresenter : BasePresenerInterface<WelcomeView> {
    fun checkConfig()
}
