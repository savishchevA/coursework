package io.bsu.mmf.helpme.view.map

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MapView : BaseView {
    fun displayPermissionsMessage()
    fun displayMap()
    fun triggerAlarm()
    fun goToSendMessageScreen()
}
