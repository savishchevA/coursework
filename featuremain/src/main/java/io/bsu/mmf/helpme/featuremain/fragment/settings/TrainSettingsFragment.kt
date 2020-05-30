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
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.TrainSettingsViewModel
import kotlinx.android.synthetic.main.fragment_train_settings.*
import org.koin.android.ext.android.inject

class TrainSettingsFragment : BaseFragment(R.layout.fragment_train_settings) {

    private val viewModel by inject<TrainSettingsViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.bg
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_back.setOnClickListener {
            navController.navigateUp()
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

        switch_train_stay_time.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                if (isChecked) {
                    showStayTimeDialog()
                } else {
                    viewModel.updateStayTime("")
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

            if (profile.stayTime.isNotEmpty()) {
                switch_train_stay_time.isChecked = true
                tv_change_stay_time.visibility = View.VISIBLE
                tv_change_stay_time.text = getString(R.string.train_time_change_value_sec, profile.stayTime)
            } else {
                tv_change_stay_time.visibility = View.GONE
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
    private fun showStayTimeDialog() {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Установите лимит по времени")
            input(
                hint = "Лимит в секундах"
            ) { _, text ->
                viewModel.updateStayTime(text.toString())
            }
            negativeButton(text = "Отмена") {
                dismiss()
            }

            positiveButton(text = "Сохранить")

        }
    }

}