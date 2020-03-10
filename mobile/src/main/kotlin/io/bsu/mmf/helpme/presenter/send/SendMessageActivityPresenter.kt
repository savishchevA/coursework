package io.bsu.mmf.helpme.presenter.send

//import io.bsu.mmf.helpme.domain.usecase.SendSosMessageUseCase
//
//import io.bsu.mmf.helpme.view.send.SendMessageView
//import moxy.InjectViewState
//import timber.log.Timber
//
//
//@InjectViewState
//class SendMessageActivityPresenter (private val sendSosMessage: SendSosMessageUseCase) : BasePresenter<SendMessageView>() {
//    private lateinit var state: SentStatus
//
//    fun applyState(newState: SentStatus?) {
//        state = newState ?: SentStatus()
//
//        if (state.sent) {
//            viewState?.showSent()
//        } else {
//            sendMessage()
//        }
//    }
//
//    fun getState(): SentStatus {
//        return state
//    }
//
//    private fun sendMessage() {
//        viewState?.showSending()
//        compositeDisposable.add(sendSosMessage.execute()
//                .subscribe({
//                    viewState.showSent()
//                    this.state.sent = true
//                }, {
//                    Timber.e(it)
//                }))
//    }
//}
