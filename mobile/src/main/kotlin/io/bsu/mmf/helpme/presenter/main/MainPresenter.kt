package io.bsu.mmf.helpme.presenter.main

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.main.MainView


interface MainPresenter: BasePresenerInterface<MainView> {
    fun fetchData()
    fun saveTime(time: Int)
}
