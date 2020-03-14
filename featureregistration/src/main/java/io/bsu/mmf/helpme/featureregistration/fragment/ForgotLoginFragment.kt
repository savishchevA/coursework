package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.baseAndroid.utils.text
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.ForgotLoginViewModel
import kotlinx.android.synthetic.main.fragment_forgot_login.*
import org.koin.android.ext.android.inject


class ForgotLoginFragment : BaseFragment(R.layout.fragment_forgot_login) {

    private val viewModel by inject<ForgotLoginViewModel>()


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