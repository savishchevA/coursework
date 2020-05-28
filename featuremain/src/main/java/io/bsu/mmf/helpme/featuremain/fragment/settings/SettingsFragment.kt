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
            requireContext(), R.color.colorSecondary
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

        tv_train_settings.setOnClickListener {
            navController.navigateSafe(R.id.toTrainSettingsFragment)
        }
    }
}