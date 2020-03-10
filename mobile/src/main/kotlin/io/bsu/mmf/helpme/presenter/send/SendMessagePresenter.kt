package io.bsu.mmf.helpme.presenter.send

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.send.SendMessageView


interface SendMessagePresenter : BasePresenerInterface<SendMessageView> {
    fun applyState(newState: SentStatus?)
    fun getState(): SentStatus
}
