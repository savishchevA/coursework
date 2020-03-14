package io.bsu.mmf.helpme.featuremain.di

import io.bsu.mmf.helpme.featuremain.fragment.MainFragment
import io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get()) }

    fragment { MainFragment() }

}