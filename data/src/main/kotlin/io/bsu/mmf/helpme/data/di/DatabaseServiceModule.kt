package io.bsu.mmf.helpme.data.di

import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.data.database.db.AppDatabase
import io.bsu.mmf.helpme.data.database.db.dao.ContactDao
import javax.inject.Singleton


@Module
class DatabaseServiceModule {

    @Singleton
    @Provides
    fun providesContactDao(db: AppDatabase): ContactDao = db.contactsDao()
}