package io.bsu.mmf.helpme.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.bsu.mmf.helpme.SosApplication
import io.bsu.mmf.helpme.data.di.*
import io.bsu.mmf.helpme.data.di.network.ServiceModule
import io.bsu.mmf.helpme.di.module.*
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ConfigModule::class,
            DataModule::class,
            AppModule::class,
            BuildersModule::class,
            ActivityModule::class,
            DataSourceModule::class,
            ServiceModule::class,
            RepositoryModule::class,
            DatabaseModule::class]
)
interface AppComponent : AndroidInjector<SosApplication> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<SosApplication>
}
