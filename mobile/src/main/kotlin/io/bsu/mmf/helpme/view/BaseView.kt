package io.bsu.mmf.helpme.view

import moxy.MvpView

interface BaseView : MvpView {
    fun showToast(message: String)
}