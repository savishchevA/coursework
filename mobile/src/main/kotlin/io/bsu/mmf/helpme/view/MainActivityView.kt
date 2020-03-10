package io.bsu.mmf.helpme.view

import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainActivityView : BaseView {
    fun updateUserLoginStatus(isNewUser: Boolean)
    fun checkRegistrationStatus(registrationStatus: Boolean)
}
