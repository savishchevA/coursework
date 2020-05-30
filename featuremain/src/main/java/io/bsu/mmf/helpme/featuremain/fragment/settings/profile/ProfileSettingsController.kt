package io.bsu.mmf.helpme.featuremain.fragment.settings.profile

import com.airbnb.epoxy.EpoxyController
import io.bsu.mmf.helpme.baseAndroid.utils.epoxy.EpoxyModelProperty
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.adapter.holder.addContactModelView
import io.bsu.mmf.helpme.featuremain.adapter.holder.contactModelView
import io.bsu.mmf.helpme.featuremain.events.AddContactEvent
import io.bsu.mmf.helpme.featuremain.events.ContactDetailEvent

class ProfileSettingsController : EpoxyController() {

    var contactsList by EpoxyModelProperty { emptyList<Contact>() }

    var callbacks: Callbacks? = null
    interface Callbacks {
        fun onContactClick(contact: Contact)
    }

    override fun buildModels() {
        contactsList.forEach {contact ->

            profileContactMovelView {
                id(contact.id)
                img(contact.contactImage)
                onContactClickListener {
                    callbacks?.onContactClick(contact)
                }
            }

        }


    }
}