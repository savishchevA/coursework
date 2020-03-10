package io.bsu.mmf.helpme.presenter.contact

import io.bsu.mmf.helpme.presenter.BasePresenerInterface
import io.bsu.mmf.helpme.view.contact.ConfigureContactView


interface ContactPresenter : BasePresenerInterface<ConfigureContactView> {
    fun fetchContactInfo()
    fun saveData(contactInfo: String, contactName: String)

}