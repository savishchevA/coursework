package io.bsu.mmf.helpme.view.contact

import io.bsu.mmf.helpme.view.BaseView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ConfigureContactView : BaseView {
    fun setData(contactInfo: String?, contactName: String?)
    fun showContactInfoEmptyError()
    fun nextScreen()
}