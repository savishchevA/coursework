package io.bsu.mmf.helpme.featureregistration.di

import io.bsu.mmf.helpme.featureregistration.fragment.*
import io.bsu.mmf.helpme.featureregistration.viewmodel.*
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {
    //auth
    viewModel { AuthViewModel() }
    viewModel { ForgotLoginViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationSecondViewModel(get()) }
    viewModel { RegistrationViewModel(get(), get()) }

    fragment { AuthFragment() }
    fragment { ForgotLoginFragment() }
    fragment { LoginFragment() }
    fragment { RegistrationFragment() }
    fragment { RegistrationSecondFragment() }


}