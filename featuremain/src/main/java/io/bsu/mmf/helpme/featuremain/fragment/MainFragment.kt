package io.bsu.mmf.helpme.featuremain.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.airbnb.epoxy.EpoxyTouchHelper
import com.github.florent37.runtimepermission.kotlin.PermissionException
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.shape.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.customview.ProgressItemDecoration
import io.bsu.mmf.helpme.baseAndroid.customview.recycler.TrainItemDecoration
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.baseAndroid.utils.dialog.input
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.adapter.controller.TrainController
import io.bsu.mmf.helpme.featuremain.adapter.holder.TrainModelView
import io.bsu.mmf.helpme.featuremain.adapter.holder.TrainModelViewModel_
import io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.bottom_sheet_training.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel by inject<MainViewModel>()


    private val trainController: TrainController = TrainController()

    private val controllerCallback = object : TrainController.Callbacks {
        override fun onTrainClick(trainItem: TrainItem) {
            deleteTrainDialog(trainItem)
        }
    }
    //  private lateinit var dialog: ChooseTimeDialogFragment


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.bg
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        askLocationPermission()
        askSendSmsPermission()
        // initContentView()
        initTrainingList()

        viewModel.trainList.observe(viewLifecycleOwner, Observer {
            trainController.trains = it
            if (it.isNotEmpty()) {
                rv_training.addItemDecoration(TrainItemDecoration(requireContext(), it))

            }
        })

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {


            tv_current_weather.text =
                getString(R.string.current_weather, (it.temp - 273.15).toInt().toString())
            tv_feel_weather.text =
                getString(R.string.feel_weather, (it.feelTemp - 273.15).toInt().toString())

            tv_current_wind.text =
                getString(R.string.wind_speed, it.windSpeed.toString())

            iv_weather.setImageDrawable(requireContext().getWeatherIcon(it.weatherIcon))

            Timber.e("Current weather: $it")
        })


        //   presenter.fetchData()

        //  presenter.getCurrentWeather()

//        start.setOnClickListener {
//            openChooseTimeDialog()
//        }
//
//        config.setOnClickListener {
//            navController.navigate(R.id.action_mainFragment_to_configureContactFragment)
//        }

        // trainController.trains = presenter.trainList
        //  rv_training.setController(trainController)

        card_train.setOnClickListener {
            navController.navigate(R.id.toTrainFragment)
            // navController.navigate(R.id.action_mainFragment_to_contactsFragment)
        }

        btn_train.setOnClickListener {
            navController.navigateDirectionSafe(
                MainFragmentDirections.actionMainFragmentToContactsFragment()
            )
        }

        btn_settings.setOnClickListener {
            navController.navigateSafe(
                R.id.toSettingsFragment
            )
        }

    }

//    private fun initContentView() {
//        val leftShapePathModel = ShapeAppearanceModel.builder()
//            .setTopLeftCorner(RoundedCornerTreatment())
//            .setTopLeftCornerSize(300f)
//            .build()
//
//        val rightBottomShapePathModel = ShapeAppearanceModel.builder()
//            .setBottomRightCorner(RoundedCornerTreatment())
//            .setBottomRightCornerSize(250f)
//            .build()
//
//
//        val leftRoundedMaterialShape = MaterialShapeDrawable(leftShapePathModel)
//        leftRoundedMaterialShape.fillColor =
//            ContextCompat.getColorStateList(requireContext(), R.color.white)
//
//        val rightBg = MaterialShapeDrawable(rightBottomShapePathModel)
//        rightBg.fillColor = ContextCompat.getColorStateList(requireContext(), R.color.bg)
//        topContent.background = rightBg
//        bottomContent.background = leftRoundedMaterialShape
//
//
//    }

    @SuppressLint("MissingPermission")
    private fun askLocationPermission() {
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) {

        } else {
            lifecycle.coroutineScope.launch {
                try {
                    askPermission(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } catch (e: PermissionException) {
                }
            }
        }
    }

    private fun deleteTrainDialog(trainItem: TrainItem) {
        MaterialDialog(requireContext()).show {
            lifecycleOwner(viewLifecycleOwner)
            title(text = "Удалить тренировку")
            message(text = "Вы действительно хотите удалить выбранную тренировку?")

            negativeButton(text = "Отмена")

            positiveButton(text = "Удалить") {
                viewModel.deleteTrain(trainItem)
            }

        }
    }

    @SuppressLint("MissingPermission")
    private fun askSendSmsPermission() {
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.SEND_SMS
            )
                    == PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) {

        } else {
            lifecycle.coroutineScope.launch {
                try {
                    askPermission(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.CALL_PHONE
                    )
                } catch (e: PermissionException) {
                }
            }
        }
    }

    private fun initTrainingList() {

        trainController.callbacks = controllerCallback
        val leftShapePathModel = ShapeAppearanceModel.builder()
            .setTopRightCorner(RoundedCornerTreatment())
            .setTopLeftCorner(RoundedCornerTreatment())
            .setTopLeftCornerSize(40f)
            .setTopRightCornerSize(40f)
            .build()

        val leftRoundedMaterialShape = MaterialShapeDrawable(leftShapePathModel)
        leftRoundedMaterialShape.fillColor =
            ContextCompat.getColorStateList(requireContext(), R.color.white)
        bottom_sheet.background = leftRoundedMaterialShape

        val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> =
            BottomSheetBehavior.from(bottom_sheet)


        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_HIDDEN
            peekHeight = training_title.height + requireContext().dpToPx(24)
                    isHideable = false
            addBottomSheetCallback(object : BottomSheetCallback() {
                override fun onStateChanged(
                    @NonNull bottomSheet: View,
                    newState: Int
                ) {
                }

                override fun onSlide(
                    @NonNull bottomSheet: View,
                    slideOffset: Float
                ) {
                }
            })
        }

        training_title.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        rv_training.setController(trainController)
    }

    fun fToC(f: Double) = 5 / 9.0 * (f - 32)


//    override fun setCurrentWeather(currentWeather: CurrentWeather) {
//        Timber.e("Current weather: ${currentWeather.temp}")
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    fun onEvent(event: EventCode) {
//      //  presenter.saveTime(event.code)
//    }

//    override fun onStop() {
//        super.onStop()
//        EventBus.getDefault().unregister(this)
//    }

//    private fun openChooseTimeDialog() {
//        dialog = ChooseTimeDialogFragment.newInstance()
//        dialog.show(childFragmentManager,
//                "add_photo_dialog_fragment")
//    }


//    override fun setCurrentConfig(contact: SosContact, message: String) {
////        contact_info.text = getString(R.string.main_contact)
////        sos_message.text = message
////        name.text = contact.contactName
////        phone.text = contact.contactInfo
//    }
//
//    override fun navigateToMap() {
//        Timber.e("try to navigate to help fragment")
//        navController.navigate(R.id.action_mainFragment_to_helpFragment)
//    }
}