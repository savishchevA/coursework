package io.bsu.mmf.helpme.presenter.auth

//import io.bsu.mmf.helpme.data.usecase.auth.ResetPasswordUseCase
//import io.bsu.mmf.helpme.data.ResultNetwork
//
//import io.bsu.mmf.helpme.view.auth.AuthView
//import io.bsu.mmf.helpme.view.auth.ForgotLoginView
//import kotlinx.coroutines.launch
//import moxy.InjectViewState
//
//
//@InjectViewState
//class ForgotLoginPresenter (
//    private val resetPasswordUseCase: ResetPasswordUseCase
//) : BasePresenter<ForgotLoginView>() {
//
//    fun resetPassword(email: String) {
//
//        localRepositoryScope.launch {
//            resetPasswordUseCase(email).fold(
//                    onSuccess = {
//                        viewState.successPasswordReset()
//                    },
//                    onFailure = {
//                        viewState.showToast((it as ResultNetwork.Error.OtherError).errorMessage)
//                    }
//            )
//        }
//
//    }
//
//}
