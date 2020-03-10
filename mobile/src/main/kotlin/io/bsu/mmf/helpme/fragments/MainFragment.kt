package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.adapter.controller.TrainController
import io.bsu.mmf.helpme.view.main.MainActivity
import io.bsu.mmf.helpme.view.map.dialog.ChooseTimeDialogFragment
import io.bsu.mmf.helpme.view.map.dialog.EventCode
import io.bsu.mmf.helpme.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainFragment : BaseFragment() {

//    @Inject
//    lateinit var daggerPresenter: Lazy<MainActivityPresenter>
//
//    @InjectPresenter
//    lateinit var presenter: MainActivityPresenter
//
//    @ProvidePresenter
//    fun providePresenter(): MainActivityPresenter = daggerPresenter.get()

    private val viewModel by inject<MainViewModel>()

    override val layout: Int
        get() = R.layout.fragment_main
//    override val basePresenter: BasePresenter<*>?
//        get() = presenter


   private val trainController: TrainController = TrainController()

    private lateinit var dialog: ChooseTimeDialogFragment

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).window.statusBarColor = ContextCompat.getColor(
                requireContext(), android.R.color.transparent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.trainList.observe(viewLifecycleOwner, Observer {
            trainController.trains = it
        })

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            Timber.e("Current weather: ${it.temp}")
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
        recyclerView.setController(trainController)


        //presenter.applyState(lastCustomNonConfigurationInstance as AlarmStatus?)
    }

//    override fun setCurrentWeather(currentWeather: CurrentWeather) {
//        Timber.e("Current weather: ${currentWeather.temp}")
//    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(event: EventCode) {
      //  presenter.saveTime(event.code)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun openChooseTimeDialog() {
        dialog = ChooseTimeDialogFragment.newInstance()
        dialog.show(childFragmentManager,
                "add_photo_dialog_fragment")
    }


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