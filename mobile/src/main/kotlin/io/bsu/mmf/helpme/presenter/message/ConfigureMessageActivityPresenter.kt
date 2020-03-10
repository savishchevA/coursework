package io.bsu.mmf.helpme.presenter.message

import io.bsu.mmf.helpme.domain.usecase.FetchMessageUseCase
import io.bsu.mmf.helpme.domain.usecase.SaveMessageUseCase
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.view.message.ConfigureMessageView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ConfigureMessageActivityPresenter @Inject constructor(
        private val fetchMessage: FetchMessageUseCase,
        private val saveMessage: SaveMessageUseCase)
    : BasePresenter<ConfigureMessageView>(){
    fun fetchMessage() {
        val message: String? = fetchMessage.execute()
        viewState.setData(message)
    }

    fun saveData(message: String) {
        if (message.isEmpty())
            viewState.showMessageEmptyError()
        else {
            saveMessage.execute(message)
            viewState.nextScreen()
        }
    }
}