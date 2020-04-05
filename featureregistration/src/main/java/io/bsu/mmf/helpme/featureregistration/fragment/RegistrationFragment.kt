package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import io.bsu.mmf.helpme.data.auth.Account
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.*
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.ext.android.inject


class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val viewModel by inject<RegistrationViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_registration.setOnClickListener {
            if(isValidFields()) {
                viewModel.createAccount(
                        Account(
                                email = email.text,
                                password = password.text

                        )
                )
            }
        }

        viewModel.successAccountCreation.observeEvent(this) {
            navController.navigate(R.id.action_registrationFragment_to_registrationSecondFragment)
        }
    }

    //TODO: move it to authDataSource
//    private fun updateUserData() {
//        auth.currentUser?.updateProfile(UserProfileChangeRequest.Builder()
//                .setDisplayName("${surname.text} ${name.text}")
//                .build())?.addOnCompleteListener {
//            if (it.isSuccessful) {
//                Toast.makeText(requireContext(), "Success registration", Toast.LENGTH_LONG).show()
//                navController.navigate(R.id.action_registrationFragment_to_registrationSecondFragment)
//            }
//        }
//    }

    private fun isValidFields(): Boolean {

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