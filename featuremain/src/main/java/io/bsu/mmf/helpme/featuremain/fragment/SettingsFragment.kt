package io.bsu.mmf.helpme.featuremain.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.SettingsViewModel
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(
    R.layout.fragment_settings
) {

    private val viewModel by inject<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}