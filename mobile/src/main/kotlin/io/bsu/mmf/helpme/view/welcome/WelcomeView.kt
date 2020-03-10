package io.bsu.mmf.helpme.view.welcome

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface WelcomeView : BaseView {
    fun showConfigNeededMessage()
    fun goToNextScreen()
}
