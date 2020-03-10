package io.bsu.mmf.helpme.view.send

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface SendMessageView : BaseView {
    fun showSending()
    fun showSent()
}
