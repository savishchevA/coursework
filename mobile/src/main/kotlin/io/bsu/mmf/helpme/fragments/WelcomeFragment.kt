package io.bsu.mmf.helpme.fragments

import android.os.Bundle
import android.view.View
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.welcome.WelcomeActivityPresenter
import io.bsu.mmf.helpme.view.welcome.WelcomeView
import kotlinx.android.synthetic.main.activity_welcome.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class WelcomeFragment : BaseFragment(), WelcomeView {

    @Inject
    lateinit var daggerPresenter: Lazy<WelcomeActivityPresenter>

    @InjectPresenter
    lateinit var presenter: WelcomeActivityPresenter

    @ProvidePresenter
    fun providePresenter(): WelcomeActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.activity_welcome
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.checkConfig()
    }

    override fun showConfigNeededMessage() {
        view?.visibility = View.VISIBLE
        button.setOnClickListener {
            navController.navigate(R.id.action_welcomeFragment_to_configureContactFragment)
        }
    }

    override fun goToNextScreen() {
        navController.navigate(R.id.action_welcomeFragment_to_mainFragment)
    }
}