package io.bsu.mmf.helpme.view.alarm

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface AlarmView : BaseView {
    fun updateTimer(i: Int)
    fun goToSendMessageScreen()
}
