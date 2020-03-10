package io.bsu.mmf.helpme

//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.bsu.mmf.helpme.data.di.commonModule
import io.bsu.mmf.helpme.di.component.DaggerAppComponent
import io.bsu.mmf.helpme.di.mobileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber
import javax.inject.Inject


class SosApplication : DaggerApplication() {

    @Inject
    lateinit var timberTree: Timber.Tree
   /* @Inject
    lateinit var crashlytics: Crashlytics*/

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initLeakCanary()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SosApplication)
            fragmentFactory()
            modules(commonModule + mobileModule)
        }
        //initCrashlytics()
    }



    private fun initTimber() {
        Timber.plant(timberTree)
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

/*    private fun initCrashlytics() {
        Fabric.with(this, crashlytics)
    }*/
}
