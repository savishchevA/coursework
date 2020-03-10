package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.domain.entity.local.Contact
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.utils.observeEvent
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.viewmodel.auth.RegistrationSecondViewModel
import kotlinx.android.synthetic.main.fragment_registration_second.*
import org.koin.android.ext.android.inject


class RegistrationSecondFragment : BaseFragment() {

    private val viewModel by inject<RegistrationSecondViewModel> ()

    override val layout: Int
        get() = R.layout.fragment_registration_second


    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        btn_late.setOnClickListener {
            navController.navigate(R.id.action_global_mainFragment)
        }

        viewModel.navigateToMainScreen.observeEvent(this, {
            navController.navigate(R.id.action_global_mainFragment)
        })
    }
}