package io.bsu.mmf.helpme.presenter

//import io.bsu.mmf.helpme.data.usecase.auth.CheckUserLoginUseCase
//import io.bsu.mmf.helpme.data.usecase.sharedPreference.GetRegistrationStatusUseCase
//import io.bsu.mmf.helpme.view.MainActivityView
//import kotlinx.coroutines.launch
//import moxy.InjectViewState
//
//
//@InjectViewState
//class MainActivityPresenter (
//        private val checkUserLoginUseCase: CheckUserLoginUseCase,
//        private val getRegistrationStatusUseCase: GetRegistrationStatusUseCase
//) : BasePresenter<MainActivityView>() {
//
//    fun checkRegistrationStatus() {
//        viewState.checkRegistrationStatus(
//                getRegistrationStatusUseCase()
//        )
//    }
//
//    fun checkUserLoginStatus() {
//        localRepositoryScope.launch {
//            viewState.updateUserLoginStatus(
//                    checkUserLoginUseCase()
//            )
//        }
//
//    }
//
//}