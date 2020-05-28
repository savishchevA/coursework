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
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegistrationSecondViewModel(get(), get()) }
    viewModel { RegistrationViewModel(get(), get(), get()) }
    viewModel { AuthMainViewModel() }

    fragment { AuthFragment() }
    fragment { AuthMainFragment() }
    fragment { ForgotLoginFragment() }
    fragment { LoginFragment() }
    fragment { RegistrationFragment() }
    fragment { RegistrationSecondFragment() }


}