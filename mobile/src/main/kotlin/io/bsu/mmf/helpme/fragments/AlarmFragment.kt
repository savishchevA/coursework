package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.alarm.AlarmActivityPresenter
import io.bsu.mmf.helpme.view.alarm.AlarmView
import kotlinx.android.synthetic.main.activity_alarm.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class AlarmFragment : BaseFragment(), AlarmView {
    @Inject
    lateinit var daggerPresenter: Lazy<AlarmActivityPresenter>

    @InjectPresenter
    lateinit var presenter: AlarmActivityPresenter

    @ProvidePresenter
    fun providePresenter(): AlarmActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.activity_alarm
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fine.setOnClickListener {
            navController.navigate(
                R.id.action_global_mainFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph_main, false)
                    .build()
            )
        }

        //presenter.applyState(lastCustomNonConfigurationInstance as AlarmStatus?)
    }

    override fun updateTimer(i: Int) {
        timer.text = resources.getQuantityString(R.plurals.alarm_seconds, i, i)
    }

    override fun goToSendMessageScreen() {
        navController.navigate(R.id.action_alarmFragment_to_sendMessageFragment)
    }
}