package io.bsu.mmf.helpme.featuremain.di

import io.bsu.mmf.helpme.featuremain.fragment.*
import io.bsu.mmf.helpme.featuremain.viewmodel.*
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel() }
    viewModel { ContactsViewModel(get(), get(), get()) }
    viewModel { AddContactViewModel(get(), get()) }
    viewModel { ChangeContactViewModel(get(), get(), get()) }
    viewModel { MapViewModel(get()) }

    viewModel { ActiveScanningViewModel() }
    viewModel { SendMessageViewModel(get(), get()) }

    fragment { MainFragment() }
    fragment { SettingsFragment() }
    fragment { ContactsFragment() }
    fragment { AddContactFragment() }
    fragment { ChangeContactFragment() }
    fragment { MapFragment() }

    fragment { ActiveScanningFragment() }
    fragment { SendMessageFragment() }




}

