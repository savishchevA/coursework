package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject


class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel by inject<LoginViewModel>()


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
                viewModel.login(Account(
                        email = email.text,
                        password = password.text
                ))

            }
        }

        forget.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_forgotLoginFragment)
        }

        btn_signup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        viewModel.successLogin.observeEvent(this, {
            navController.navigate(R.id.action_loginFragment_to_mainFragment)
        })

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