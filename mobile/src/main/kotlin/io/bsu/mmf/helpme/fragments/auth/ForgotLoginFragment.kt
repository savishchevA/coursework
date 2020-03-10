package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.auth.ForgotLoginPresenter
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.view.auth.ForgotLoginView
import kotlinx.android.synthetic.main.fragment_forgot_login.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject


class ForgotLoginFragment : BaseFragment(), ForgotLoginView {
    @Inject
    lateinit var daggerPresenter: Lazy<ForgotLoginPresenter>

    @InjectPresenter
    lateinit var presenter: ForgotLoginPresenter

    @ProvidePresenter
    fun providePresenter(): ForgotLoginPresenter = daggerPresenter.get()


    override val layout: Int
        get() = R.layout.fragment_forgot_login
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_reset.setOnClickListener {
            presenter.resetPassword(email.text)
        }
    }

    override fun successPasswordReset() {
        navController.navigateUp()
    }

}