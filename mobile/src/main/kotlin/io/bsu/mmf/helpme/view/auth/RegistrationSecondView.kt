package io.bsu.mmf.helpme.view.auth

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface RegistrationSecondView : BaseView {
    fun navigateToMainScreen()

}