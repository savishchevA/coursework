package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.ContactSettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactSettingsFragment : BaseFragment(R.layout.fragment_contact_settings) {

    private val viewModel by viewModel<ContactSettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}