package io.bsu.mmf.helpme.presenter.map

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.map.MapView


interface MapPresenter : BasePresenerInterface<MapView> {
    fun permissionsMissing()
    fun startMonitoringLocation()
    fun helpNeeded()

    fun saveCurrentTime(time: Int)
}
