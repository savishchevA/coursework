package io.bsu.mmf.helpme.di

import io.bsu.mmf.helpme.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mobileModule = module {


    //main
    viewModel { MainActivityViewModel(get(), get()) }


}