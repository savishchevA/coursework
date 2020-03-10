package io.bsu.mmf.helpme.presenter.map

//import io.bsu.mmf.helpme.domain.usecase.AlertOnUserIsStillUseCase
//
//import io.bsu.mmf.helpme.view.map.MapView
//import io.reactivex.android.schedulers.AndroidSchedulers
//import moxy.InjectViewState
//import timber.log.Timber
//
//
//@InjectViewState
//class MapActivityPresenter (
//        private val alertOnUserIsStill: AlertOnUserIsStillUseCase
//                                               ) : BasePresenter<MapView>() {
//
//    fun permissionsMissing() {
//        viewState?.displayPermissionsMessage()
//    }
//
//    fun startMonitoringLocation() {
//        viewState?.displayMap()
//        compositeDisposable.add(
//                alertOnUserIsStill.execute()
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({
//                            viewState.triggerAlarm()
//                        }, {
//                            Timber.e(it)
//                        }, {
//                            Timber.w("completed without user stillness")
//                        })
//        )
//    }
//
//    fun helpNeeded() {
//        viewState.goToSendMessageScreen()
//    }
//
//    fun saveCurrentTime(time: Int) {
//
//    }
//}
