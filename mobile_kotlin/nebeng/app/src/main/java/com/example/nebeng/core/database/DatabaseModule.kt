package com.example.nebeng.core.database

import android.content.Context
import androidx.room.Room
import com.example.nebeng.feature_auth.data.local.dao.AuthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "nebeng.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideAuthDao(database: AppDatabase): AuthDao {
        return database.authDao()
    }
}