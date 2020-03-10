package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.utils.observeEvent
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.viewmodel.auth.ForgotLoginViewModel
import kotlinx.android.synthetic.main.fragment_forgot_login.*
import org.koin.android.ext.android.inject


class ForgotLoginFragment : BaseFragment() {

    private val viewModel by inject<ForgotLoginViewModel>()

    override val layout: Int
        get() = R.layout.fragment_forgot_login


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_reset.setOnClickListener {
            viewModel.resetPassword(email.text)
        }

        viewModel.successPasswordReset.observeEvent(this, {
            navController.navigateUp()
        })
    }

}