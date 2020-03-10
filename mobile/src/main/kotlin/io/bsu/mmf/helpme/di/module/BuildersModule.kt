package io.bsu.mmf.helpme.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.bsu.mmf.helpme.fragments.*
import io.bsu.mmf.helpme.fragments.auth.*
import javax.inject.Scope


@Module
abstract class BuildersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAuthFragment(): AuthFragment


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeRegistrationFragment(): RegistrationFragment


    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeRegistrationSecondFragment(): RegistrationSecondFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeForgotLoginFragment(): ForgotLoginFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAlarmFragment(): AlarmFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeConfigureContactFragment(): ConfigureContactFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeConfigureMessageFragment(): ConfigureMessageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHelpFragment(): HelpFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSendMessageFragment(): SendMessageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeWelcomFragment(): WelcomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSosMessageFragment(): SosMessageFragment

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class FragmentScoped
