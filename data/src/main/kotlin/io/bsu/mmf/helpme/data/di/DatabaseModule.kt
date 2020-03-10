package io.bsu.mmf.helpme.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.bsu.mmf.helpme.data.database.db.AppDatabase
import javax.inject.Singleton

private const val DATABASE_NAME = "sosapp.db"

@Module(includes = [DatabaseServiceModule::class])
class DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        val builder = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
           // .addMigrations(*ALL_MIGRATIONS)
        return builder.build()
    }
}
