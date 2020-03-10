package io.bsu.mmf.helpme.view.message

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ConfigureMessageView : BaseView {
    fun setData(message: String?)
    fun showMessageEmptyError()
    fun nextScreen()
}