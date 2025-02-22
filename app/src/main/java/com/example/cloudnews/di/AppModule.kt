package com.example.cloudnews.di

import android.app.Application
import com.example.cloudnews.data.manager.LocalUserManagerImpl
import com.example.cloudnews.domain.manager.LocalUserManager
import com.example.cloudnews.domain.usecases.AppEntryUseCases
import com.example.cloudnews.domain.usecases.ReadAppEntry
import com.example.cloudnews.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun providesAppEntryUseCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
}