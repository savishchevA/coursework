package io.bsu.mmf.helpme.featureregistration.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import com.github.razir.progressbutton.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.baseAndroid.utils.dialog.SimpleKeyboardAnimator
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.AuthMainViewModel
import io.bsu.mmf.helpme.featureregistration.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel by inject<LoginViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SimpleKeyboardAnimator(requireActivity().window).start()
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
                viewModel.login(
                    Account(
                        email = email.text,
                        password = password.text
                    )
                )
                activity?.hideKeyboard()

            }
        }

        btn_back.setOnClickListener {
            navController.navigateUp()
        }

        content.setTopRoundedBackground()

        forget.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_forgotLoginFragment)
        }

        btn_signup.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        viewModel.successLogin.observe(viewLifecycleOwner, Observer {
           // navController.navigate(R.id.action_global_mainFragment)
            (parentFragment?.parentFragment as AuthMainFragment).navigateToMainScreen()
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })

    }


    fun showProgressBar() {
        btn_login.showProgress {
            progressColor = R.color.bg
            progressRadiusRes = R.dimen.margin_10
            gravity = DrawableButton.GRAVITY_CENTER
        }
        this.view?.isEnabled = false
    }

    fun hideProgressBar() {
        btn_login.isEnabled = true
        btn_login.hideProgress(R.string.login)
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