package io.bsu.mmf.helpme

import io.bsu.mmf.helpme.data.di.commonModule
import io.bsu.mmf.helpme.data.repository.sharedPreference.SharedPreferenceRepository
import io.bsu.mmf.helpme.di.mobileModule
import io.bsu.mmf.helpme.viewmodel.auth.AuthViewModel
import io.bsu.mmf.helpme.viewmodel.auth.RegistrationSecondViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.get
import org.koin.core.inject
import org.koin.dsl.module
import org.koin.test.KoinTest

class MyKoinTest : KoinTest {

    val viewModel by inject<RegistrationSecondViewModel>()

    @Test
    fun injectMyComponent() {
        startKoin{
            modules(commonModule, mobileModule)

            val componentA = get<RegistrationSecondViewModel>()

            assertEquals(componentA, viewModel)
        }
    }

}