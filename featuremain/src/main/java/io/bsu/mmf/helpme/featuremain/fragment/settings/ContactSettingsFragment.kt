package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.dialog.input
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.ContactSettingsViewModel
import kotlinx.android.synthetic.main.fragment_contact_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactSettingsFragment : BaseFragment(R.layout.fragment_contact_settings) {

    private val viewModel by viewModel<ContactSettingsViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.message_settings
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        switch_common_message.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    showCommonMessageDialog()
                } else {
                    viewModel.updateCommonMessage("")
                }
            }
        }
        switch_all_contact.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                viewModel.setSendAllContacts(isChecked)
            }
        }


        viewModel.profile.observeEvent(viewLifecycleOwner) { profile ->
            if (profile.commonMessage.isNotEmpty()) {
                switch_common_message.isChecked = true
                tv_common_message.visibility = View.VISIBLE
                tv_common_message.text = profile.commonMessage
            } else {
                tv_common_message.visibility = View.GONE
            }
        }

        viewModel.sendAllContacts.observeEvent(viewLifecycleOwner) {
            switch_all_contact.isChecked = it
        }


        btn_back.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun showCommonMessageDialog() {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Введите сообщение")
            input(
                hint = "Сообщение"
            ) { _, text ->
                viewModel.updateCommonMessage(text.toString())
            }
            negativeButton(text = "Отмена") {
                dismiss()
            }

            positiveButton(text = "Сохранить")

        }
    }


}