package io.bsu.mmf.helpme.presenter.message

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.message.ConfigureMessageView


interface MessagePresenter : BasePresenerInterface<ConfigureMessageView> {
    fun fetchMessage()
    fun saveData(message: String)
}