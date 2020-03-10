package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.pawegio.kandroid.e
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.auth.AuthPresenter
import io.bsu.mmf.helpme.presenter.auth.LoginPresenter
import io.bsu.mmf.helpme.utils.afterTextChanged
import io.bsu.mmf.helpme.utils.isEmailValid
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.view.auth.AuthView
import io.bsu.mmf.helpme.view.auth.LoginView
import kotlinx.android.synthetic.main.fragment_login.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject


class LoginFragment : BaseFragment(), LoginView {
    @Inject
    lateinit var daggerPresenter: Lazy<LoginPresenter>

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.fragment_login
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email.afterTextChanged {
            if (email.error != null) {
                email.error = null
            }
        }

        password.afterTextChanged {
            if (password.error != null) {
                password.error = null
            }
        }

        btn_login.setOnClickListener {
            if (validateField()) {
                presenter.login(
                        Account(
                                email = email.text,
                                password = password.text
                        )
                )
            }
        }

        forget.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_forgotLoginFragment)
        }

        btn_signup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    override fun successLogin() {
        navController.navigate(R.id.action_loginFragment_to_mainFragment)
    }

    private fun validateField(): Boolean {

        return if (!isEmailValid(email.text)) {
            email.error = "Invalid email"
            false
        } else if (password.text.isEmpty()) {
            password.error = "Field must be filled in"
            false
        } else {
            true
        }
    }
}