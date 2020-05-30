package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.dialog.input
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.fragment.settings.profile.ProfileSettingsController
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.ProfileSettingsViewModel
import kotlinx.android.synthetic.main.fragment_profile_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileSettingsFragment : BaseFragment(R.layout.fragment_profile_settings) {


    private val viewModel by viewModel<ProfileSettingsViewModel>()

    private val controller = ProfileSettingsController()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.colorOnSurface
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller.callbacks = controllerCallback
        rv_contacts.setController(controller)

        switch_alarm_time.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    showAlarmTimeDialog()
                } else {
                    viewModel.updateAlarmTime("")
                }
            }
        }

        btn_back.setOnClickListener {
            navController.navigateUp()
        }

        viewModel.profile.observeEvent(viewLifecycleOwner) {profile ->
            if (profile.alarmTime.isNotEmpty()) {

                switch_alarm_time.isChecked = true
                tv_alarm_time.visibility = View.VISIBLE
                tv_alarm_time.text = getString(R.string.profile_settings_alarm_value, profile.alarmTime)
            } else {
                tv_alarm_time.visibility = View.GONE
            }

        }

        viewModel.allContacts.observe(viewLifecycleOwner, Observer {
            controller.contactsList = it
        })

        viewModel.primaryContact.observe(viewLifecycleOwner, Observer {
            updatePrimaryContainer(it)
        })
    }

    private fun showAlarmTimeDialog() {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Установите тревожное вреся")
            input(
                hint = "Время в секундах"
            ) { _, text ->
                viewModel.updateAlarmTime(text.toString())
            }
            negativeButton(text = "Отмена") {
                dismiss()
            }

            positiveButton(text = "Сохранить")

        }
    }

    private fun updatePrimaryContainer(contact: Contact) {
        if (contact.contactImage != null)
            contact_img.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    contact.contactImage,
                    0,
                    contact.contactImage!!.size
                )
            )

        tv_contact_name.text = getString(R.string.profile_settings_contact_name, contact.name)
        tv_contact_phone.text = getString(R.string.profile_settings_contact_phone, contact.phoneNumber)
        tv_contact_address.text = getString(R.string.profile_settings_contact_address, contact.address)
    }

    private val controllerCallback = object : ProfileSettingsController.Callbacks {
        override fun onContactClick(contact: Contact) {
            viewModel.onContactClick(contact)
        }
    }
}