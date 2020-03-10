package io.bsu.mmf.helpme.fragments.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.domain.auth.Account
import io.bsu.mmf.helpme.fragments.BaseFragment
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.auth.RegistrationPresenter
import io.bsu.mmf.helpme.utils.isEmailValid
import io.bsu.mmf.helpme.utils.text
import io.bsu.mmf.helpme.view.auth.RegistrationView
import kotlinx.android.synthetic.main.fragment_registration.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class RegistrationFragment : BaseFragment(), RegistrationView {
    @Inject
    lateinit var daggerPresenter: Lazy<RegistrationPresenter>

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter(): RegistrationPresenter = daggerPresenter.get()


    override val layout: Int
        get() = R.layout.fragment_registration
    override val basePresenter: BasePresenter<*>?
        get() = presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_registration.setOnClickListener {
            if(isValidFields()) {
                presenter.createAccount(Account(
                        email = email.text,
                        password = password.text

                ))
            }
        }
    }

    override fun successAccountCreation() {
       // updateUserData()
        navController.navigate(R.id.action_registrationFragment_to_registrationSecondFragment)
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