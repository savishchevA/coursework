package io.bsu.mmf.helpme.presenter.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import io.bsu.mmf.helpme.data.usecase.weather.GetCurrentWeatherUseCase
import io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenViewModelTest {

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var mainViewModel: io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel

    @Before
    fun setup() {
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(mock())
        mainViewModel = io.bsu.mmf.helpme.featuremain.viewmodel.MainViewModel(
            getCurrentWeatherUseCase
        )

    }

    @Test
    fun testSendingRequest() {

        mainViewModel.triggeredEvent()


    }

}