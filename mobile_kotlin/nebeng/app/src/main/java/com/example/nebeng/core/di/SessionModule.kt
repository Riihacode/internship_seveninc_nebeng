package com.example.nebeng.core.di

import android.content.Context
import com.example.nebeng.core.session.UserPreferences
import com.example.nebeng.core.session.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionModule {
    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPrefs: UserPreferences): UserPreferencesRepository {
        return UserPreferencesRepository(userPrefs)
    }
}