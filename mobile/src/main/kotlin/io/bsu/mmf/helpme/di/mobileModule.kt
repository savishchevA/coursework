package io.bsu.mmf.helpme.di

import io.bsu.mmf.helpme.fragments.AlarmFragment
import io.bsu.mmf.helpme.fragments.MainFragment
import io.bsu.mmf.helpme.fragments.auth.ForgotLoginFragment
import io.bsu.mmf.helpme.fragments.auth.LoginFragment
import io.bsu.mmf.helpme.fragments.auth.RegistrationFragment
import io.bsu.mmf.helpme.fragments.auth.RegistrationSecondFragment
import io.bsu.mmf.helpme.viewmodel.MainActivityViewModel
import io.bsu.mmf.helpme.viewmodel.MainViewModel
import io.bsu.mmf.helpme.viewmodel.auth.*
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mobileModule = module {



    //auth
    viewModel { AuthViewModel() }
    viewModel { ForgotLoginViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationSecondViewModel(get()) }
    viewModel { RegistrationViewModel(get(), get()) }

    fragment { AlarmFragment() }
    fragment { ForgotLoginFragment() }
    fragment { LoginFragment() }
    fragment { RegistrationFragment() }
    fragment { RegistrationSecondFragment() }

    //main
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }

    fragment { MainFragment() }



}