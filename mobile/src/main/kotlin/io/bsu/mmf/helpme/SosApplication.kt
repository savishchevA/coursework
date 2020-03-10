package io.bsu.mmf.helpme

import android.app.Application
import io.bsu.mmf.helpme.data.di.commonModule
import io.bsu.mmf.helpme.di.mobileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


class SosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SosApplication)
            fragmentFactory()
            modules(commonModule, mobileModule)
        }
    }
}
