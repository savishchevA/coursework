package io.bsu.mmf.helpme.presenter.alarm

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.alarm.AlarmView


interface AlarmPresenter : BasePresenerInterface<AlarmView> {
    fun applyState(newState: AlarmStatus?)
    fun getState(): AlarmStatus
}
