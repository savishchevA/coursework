package io.bsu.mmf.helpme.presenter.auth
//
//import io.bsu.mmf.helpme.data.usecase.auth.CheckUserLoginUseCase
//import io.bsu.mmf.helpme.data.usecase.auth.LoginWithEmailUseCase
//import io.bsu.mmf.helpme.data.auth.Account
//
//import io.bsu.mmf.helpme.view.auth.AuthView
//import io.bsu.mmf.helpme.view.auth.LoginView
//import kotlinx.coroutines.launch
//import moxy.InjectViewState
//import timber.log.Timber
//
//
//@InjectViewState
//class LoginPresenter (
//    private val loginWithEmailUseCase: LoginWithEmailUseCase
//) : BasePresenter<LoginView>() {
//
//    fun login(account: Account) {
//        localRepositoryScope.launch {
//            loginWithEmailUseCase(account).fold(
//                    onSuccess = {
//                        viewState.successLogin()
//                    },
//                    onFailure = {
//
//                    }
//            )
//        }
//    }
//
//}