package com.navdroid.kotlinFlowSample.di

import android.content.Context
import com.navdroid.kotlinFlowSample.dao.MessageDao
import com.navdroid.kotlinFlowSample.database.MessageDatabase
import com.navdroid.kotlinFlowSample.repo.MessageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MessageDatabase =
        MessageDatabase.getDatabaseInstance(context)

    @Provides
    fun provideDao(database: MessageDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    fun provideMessageRepo(messageDao: MessageDao): MessageRepo = MessageRepo(messageDao)
}
