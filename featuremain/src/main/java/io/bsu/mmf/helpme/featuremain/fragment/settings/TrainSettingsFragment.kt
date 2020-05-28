package io.bsu.mmf.helpme.featuremain.fragment.settings

import android.os.Bundle
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.dialog.input
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.TrainSettingsViewModel
import kotlinx.android.synthetic.main.fragment_train_settings.*
import org.koin.android.ext.android.inject

class TrainSettingsFragment : BaseFragment(R.layout.fragment_train_settings) {

    private val viewModel by inject<TrainSettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(toolbarTrainSettings) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                navController.navigateUp()
            }
        }
//
//        btn_update_distance.setOnClickListener {
//            viewModel.updateTrainDistance(trainDistance.text)
//        }

        switch_train_distance.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) {
                if (isChecked) {
                    showDistanceDialog()
                } else {
                    viewModel.updateTrainDistance("")
                }
            }
        }

        switch_train_time.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) {
                if (isChecked) {
                    showTimeDialog()
                } else {
                    viewModel.updateTrainTime("")
                }
            }
        }

        tv_change_distance.setOnClickListener {
            showDistanceDialog()
        }

        tv_change_time.setOnClickListener {
            showTimeDialog()
        }

        viewModel.profile.observeEvent(viewLifecycleOwner) { profile ->
            if (profile.trainDistance.isNotEmpty()) {
                switch_train_distance.isChecked = true
                tv_change_distance.visibility = View.VISIBLE
                tv_change_distance.text =
                    getString(R.string.train_distance_change_value, profile.trainDistance)
            } else {
                tv_change_distance.visibility = View.GONE
            }

            if (profile.trainTime.isNotEmpty()) {
                switch_train_time.isChecked = true
                tv_change_time.visibility = View.VISIBLE
                tv_change_time.text = getString(R.string.train_time_change_value, profile.trainTime)
            } else {
                tv_change_time.visibility = View.GONE
            }
        }

        viewModel.navigateUp.observeEvent(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    private fun showDistanceDialog() {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Установите лимит по дистанции")
            input(
                hint = "Лимит в км"
            ) { _, text ->
                viewModel.updateTrainDistance(text.toString())
            }
            negativeButton(text = "Отмена") {
                dismiss()
            }

            positiveButton(text = "Сохранить")

        }
    }

    private fun showTimeDialog() {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Установите лимит по времени")
            input(
                hint = "Лимит в минутах"
            ) { _, text ->
                viewModel.updateTrainTime(text.toString())
            }
            negativeButton(text = "Отмена") {
                dismiss()
            }

            positiveButton(text = "Сохранить")

        }
    }

}