package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.setTopRoundedBackground
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_auth.*
import org.koin.android.ext.android.inject

class AuthFragment : BaseFragment(R.layout.fragment_auth) {


    private val viewModel by inject<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        root.setTopRoundedBackground()

        btn_login.setOnClickListener {
            navController.navigate(R.id.fromAuthToLogin)
        }

        btn_registration.setOnClickListener {
            navController.navigate(R.id.fromAuthToRegistration)
        }
    }


}