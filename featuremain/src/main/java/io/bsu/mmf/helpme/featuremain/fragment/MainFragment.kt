package io.bsu.mmf.helpme.featuremain.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.Observer
import com.airbnb.epoxy.EpoxyTouchHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.shape.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.customview.ProgressItemDecoration
import io.bsu.mmf.helpme.baseAndroid.customview.recycler.TrainItemDecoration
import io.bsu.mmf.helpme.baseAndroid.utils.navigateDirectionSafe
import io.bsu.mmf.helpme.baseAndroid.utils.navigateSafe
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.adapter.controller.TrainController
import io.bsu.mmf.helpme.featuremain.adapter.holder.TrainModelView
import io.bsu.mmf.helpme.featuremain.adapter.holder.TrainModelViewModel_
import io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.bottom_sheet_training.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel by inject<MainViewModel>()


    private val trainController: TrainController = TrainController()

    //  private lateinit var dialog: ChooseTimeDialogFragment


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), android.R.color.transparent
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initContentView()
        //  initTrainingList()
//
//        viewModel.trainList.observe(viewLifecycleOwner, Observer {
//            trainController.trains = it
//            rv_training.addItemDecoration(TrainItemDecoration(requireContext(), it))
//        })

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {


            tv_current_weather.text =
                getString(R.string.current_weather, (it.temp - 273.15).toInt().toString())
            Timber.e("Current weather: ${it.temp - 273.15}")
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

        btn_train.setOnClickListener {
            navController.navigate(R.id.toTrainFragment)
            // navController.navigate(R.id.action_mainFragment_to_contactsFragment)
        }

        btn_contact.setOnClickListener {
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

    private fun initContentView() {
        val leftShapePathModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setTopLeftCornerSize(300f)
            .build()

        val rightBottomShapePathModel = ShapeAppearanceModel.builder()
            .setBottomRightCorner(RoundedCornerTreatment())
            .setBottomRightCornerSize(250f)
            .build()


        val leftRoundedMaterialShape = MaterialShapeDrawable(leftShapePathModel)
        leftRoundedMaterialShape.fillColor =
            ContextCompat.getColorStateList(requireContext(), R.color.white)

        val rightBg = MaterialShapeDrawable(rightBottomShapePathModel)
        rightBg.fillColor = ContextCompat.getColorStateList(requireContext(), R.color.bg)
        topContent.background = rightBg
        bottomContent.background = leftRoundedMaterialShape


    }

    private fun initTrainingList() {

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
            peekHeight = 240
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