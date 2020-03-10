package io.bsu.mmf.helpme.presenter

interface BasePresenerInterface<View> {
    fun bind(view: View)
    fun unbind()
}