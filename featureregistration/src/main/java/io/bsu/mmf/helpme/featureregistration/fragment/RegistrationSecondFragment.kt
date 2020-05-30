package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.RegistrationSecondViewModel
import kotlinx.android.synthetic.main.fragment_registration_second.*
import org.koin.android.ext.android.inject


class RegistrationSecondFragment : BaseFragment(R.layout.fragment_registration_second) {

    private val viewModel by inject<RegistrationSecondViewModel> ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        root.setTopRoundedBackground()

        btn_continue.setOnClickListener {
            viewModel.saveContact(
                    Contact(
                            name = contact_name.text,
                            phoneNumber = contact_phone.text,
                            message = contact_message.text,
                            address = contact_address.text,
                            isPriorityContact = true
                    )
            )
        }


        viewModel.navigateToMainScreen.observeEvent(this) {
            (parentFragment?.parentFragment as AuthMainFragment).navigateToMainScreen()
        }
    }
}