package io.bsu.mmf.helpme

//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.bsu.mmf.helpme.di.component.DaggerAppComponent
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
