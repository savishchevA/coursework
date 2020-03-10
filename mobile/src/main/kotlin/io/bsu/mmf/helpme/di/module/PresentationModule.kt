package io.bsu.mmf.helpme.di.module

//
//@Module
//class PresentationModule {
//    @Provides
//    @Singleton
//    fun provideWelcomePresenter(checkConfig: CheckHasConfigUseCase): WelcomePresenter {
//        return WelcomeActivityPresenter(checkConfig)
//    }
//
//    @Provides
//    @Singleton
//    fun provideContactPresenter(fetchContactUseCase: FetchContactUseCase, saveContactUseCase: SaveContactUseCase): ContactPresenter {
//        return ConfigureContactActivityPresenter(fetchContactUseCase, saveContactUseCase)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMessagePresenter(fetchMessage: FetchMessageUseCase, saveMessage: SaveMessageUseCase): MessagePresenter {
//        return ConfigureMessageActivityPresenter(fetchMessage, saveMessage)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMainPresenter(fetchContact: FetchContactUseCase, fetchMessage: FetchMessageUseCase, fetchTime: FetchTimeUseCase): MainPresenter {
//        return MainActivityPresenter(fetchContact, fetchMessage, fetchTime)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMapPresenter(alertOnUserIsStill: AlertOnUserIsStillUseCase): MapPresenter {
//        return MapActivityPresenter(alertOnUserIsStill)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAlarmPresenter(playSound: PlaySoundUseCase, @Named("timeToWaitBeforeSendingMessage") timeToWait: Long): AlarmPresenter {
//        return AlarmActivityPresenter(playSound, timeToWait)
//    }
//
//    @Provides
//    @Singleton
//    fun provideSendMessagePresenter(sendSosMessage: SendSosMessageUseCase): SendMessagePresenter {
//        return SendMessageActivityPresenter(sendSosMessage)
//    }
//
//
//}
