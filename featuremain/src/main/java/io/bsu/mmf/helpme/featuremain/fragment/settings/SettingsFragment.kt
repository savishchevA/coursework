package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.navigateSafe
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(R.layout.fragment_setting) {


    private val viewModel by inject<SettingsViewModel>()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.btn_main_start
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(toolbarSettings) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                navController.navigateUp()
            }
        }

        card_train_settings.setOnClickListener {
            navController.navigateSafe(R.id.toTrainSettingsFragment)
        }

        card_contact_settings.setOnClickListener {
            navController.navigateSafe(R.id.toContactsSettingsFragment)
        }

        card_profile_settings.setOnClickListener {
            navController.navigateSafe(R.id.toProfileSettingsFragment)
        }

        card_app_settings.setOnClickListener {
            navController.navigateSafe(R.id.toAboutAppFragment)
        }
    }
}