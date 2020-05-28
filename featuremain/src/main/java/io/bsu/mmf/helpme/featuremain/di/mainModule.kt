package io.bsu.mmf.helpme.featuremain.di

import io.bsu.mmf.helpme.featuremain.fragment.*
import io.bsu.mmf.helpme.featuremain.fragment.chat.ChatFragment
import io.bsu.mmf.helpme.featuremain.fragment.settings.*
import io.bsu.mmf.helpme.featuremain.fragment.train.TrainFragment
import io.bsu.mmf.helpme.featuremain.fragment.train.TrainListFragment
import io.bsu.mmf.helpme.featuremain.fragment.train.finish.HelpFragment
import io.bsu.mmf.helpme.featuremain.fragment.train.finish.SuccessTrainFragment
import io.bsu.mmf.helpme.featuremain.viewmodel.*
import io.bsu.mmf.helpme.featuremain.viewmodel.settings.*
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { ContactsViewModel(get(), get(), get()) }
    viewModel { AddContactViewModel(get(), get()) }
    viewModel { ChangeContactViewModel(get(), get(), get()) }
    viewModel { MapViewModel(get()) }
    viewModel { TrainListViewModel() }
    viewModel { ChatViewModel() }
    viewModel { TrainViewModel(get(), get()) }

    viewModel { ActiveScanningViewModel() }
    viewModel { SendMessageViewModel(get(), get()) }
    viewModel { SuccessTrainViewModel() }
    viewModel { HelpViewModel() }

    //settings
    viewModel { SettingsViewModel() }
    viewModel { ContactSettingsViewModel() }
    viewModel { ProfileSettingsViewModel() }
    viewModel { TrainSettingsViewModel(get(), get(), get()) }

    fragment { MainFragment() }
    fragment { ContactsFragment() }
    fragment { AddContactFragment() }
    fragment { ChangeContactFragment() }
    fragment { MapFragment() }
    fragment { TrainListFragment() }
    fragment { ChatFragment() }
    fragment { TrainFragment() }

    fragment { ActiveScanningFragment() }
    fragment { SendMessageFragment() }
    fragment { SuccessTrainFragment() }
    fragment { HelpFragment() }

    //settings
    fragment { SettingsFragment() }
    fragment { TrainSettingsFragment() }
    fragment { ContactSettingsFragment() }
    fragment { ProfileSettingsFragment() }




}

