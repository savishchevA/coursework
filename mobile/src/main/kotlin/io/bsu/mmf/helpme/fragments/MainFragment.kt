package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.adapter.controller.TrainController
import io.bsu.mmf.helpme.domain.entity.SosContact
import io.bsu.mmf.helpme.domain.weather.CurrentWeather
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.main.MainActivityPresenter
import io.bsu.mmf.helpme.view.main.MainActivity
import io.bsu.mmf.helpme.view.main.MainView
import io.bsu.mmf.helpme.view.map.dialog.ChooseTimeDialogFragment
import io.bsu.mmf.helpme.view.map.dialog.EventCode
import kotlinx.android.synthetic.main.fragment_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.greenrobot.eventbus.*
import timber.log.Timber
import javax.inject.Inject

class MainFragment : BaseFragment(), MainView {

    @Inject
    lateinit var daggerPresenter: Lazy<MainActivityPresenter>

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.fragment_main
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    @Inject
    lateinit var trainController: TrainController

    private lateinit var dialog: ChooseTimeDialogFragment

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).window.statusBarColor = requireContext().getColor(android.R.color.transparent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.fetchData()

        presenter.getCurrentWeather()

//        start.setOnClickListener {
//            openChooseTimeDialog()
//        }
//
//        config.setOnClickListener {
//            navController.navigate(R.id.action_mainFragment_to_configureContactFragment)
//        }

        trainController.trains = presenter.trainList
        recyclerView.setController(trainController)


        //presenter.applyState(lastCustomNonConfigurationInstance as AlarmStatus?)
    }

    override fun setCurrentWeather(currentWeather: CurrentWeather) {
        Timber.e("Current weather: ${currentWeather.temp}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(event: EventCode) {
        presenter.saveTime(event.code)
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


    override fun setCurrentConfig(contact: SosContact, message: String) {
//        contact_info.text = getString(R.string.main_contact)
//        sos_message.text = message
//        name.text = contact.contactName
//        phone.text = contact.contactInfo
    }

    override fun navigateToMap() {
        Timber.e("try to navigate to help fragment")
        navController.navigate(R.id.action_mainFragment_to_helpFragment)
    }
}