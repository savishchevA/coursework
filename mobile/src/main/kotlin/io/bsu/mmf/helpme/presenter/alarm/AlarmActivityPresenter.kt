package io.bsu.mmf.helpme.presenter.alarm

//import io.bsu.mmf.helpme.domain.usecase.PlaySoundUseCase
//
//import io.reactivex.Observable
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.schedulers.Schedulers
//import moxy.InjectViewState
//import timber.log.Timber
//import java.util.concurrent.TimeUnit
//
//import javax.inject.Named
//
//
//class AlarmActivityPresenter (
//    private val playSound: PlaySoundUseCase,
//
//   @Named(value = "timeToWaitBeforeSendingMessage") private val timeToWait: Long
//) : BasePresenter<AlarmView>() {
//    private lateinit var state: AlarmStatus
//
//    fun applyState(newState: AlarmStatus?) {
//        state = newState ?: AlarmStatus(timeToWait)
//
//        if (state.seconds > 0) {
//            startAlarm(state.seconds)
//        } else {
//            viewState?.goToSendMessageScreen()
//        }
//    }
//
//    fun getState(): AlarmStatus {
//        return state
//    }
//
//    private fun startAlarm(seconds: Long) {
//        val timer = Observable.interval(1, TimeUnit.SECONDS)
//            .take(seconds + 1)
//            .map { (seconds - it).toInt() }
//
//        val timerWithSound = Observable.using({
//            playSound.prepare()
//            playSound
//        }, { playSound ->
//            timer.doOnNext { playSound.execute() }
//        }, {
//            it.finish()
//        })
//
//        compositeDisposable.add(timerWithSound
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                state.seconds = it.toLong()
//                viewState.updateTimer(it)
//            }, {
//                Timber.e(it)
//            }, {
//                viewState.goToSendMessageScreen()
//            })
//        )
//    }
//}
