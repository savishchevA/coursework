package io.bsu.mmf.helpme.featuremain.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.ChangeContactViewModel
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.android.synthetic.main.fragment_change_contact.*
import kotlinx.android.synthetic.main.fragment_change_contact.contact_address
import kotlinx.android.synthetic.main.fragment_change_contact.contact_message
import kotlinx.android.synthetic.main.fragment_change_contact.contact_name
import kotlinx.android.synthetic.main.fragment_change_contact.contact_phone
import org.koin.android.ext.android.inject

class ChangeContactFragment : BaseFragment(R.layout.fragment_change_contact) {

    private val viewModel by inject<ChangeContactViewModel>()

    val args by navArgs<ChangeContactFragmentArgs>()

    private lateinit var currentContact: Contact

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentContact(args.contactId)

        viewModel.currentContact.observe(viewLifecycleOwner, Observer {
            currentContact = it
            setCurrentContactData(it)
        })

        btn_change.setOnClickListener {
            viewModel.getAddressCoordinate(contact_address.text)
        }

        viewModel.addressCoordinate.observe(viewLifecycleOwner, Observer {
            viewModel.updateContact(
                Contact(
                    id = currentContact.id,
                    name = contact_name.text,
                    phoneNumber = contact_phone.text,
                    message = contact_message.text,
                    address = contact_address.text,
                    longitude = it.longitude,
                    latitude = it.latitude,
                    contactImage = currentContact.contactImage,
                    isPriorityContact = false
                )
            )
        })

        viewModel.updateContactEvent.observeEvent(viewLifecycleOwner) {
            navController.navigateUp()
        }

    }

    private fun setCurrentContactData(contact: Contact) {
        contact_name.setText(contact.name)
        contact_address.setText(contact.address)
        contact_message.setText(contact.message)
        contact_phone.setText(contact.phoneNumber)
    }

}