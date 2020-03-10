package io.bsu.mmf.helpme.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.bsu.mmf.helpme.view.main.MainActivity
import javax.inject.Scope

/**
 * Module to contribute all the activities.
 */
@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}

@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped