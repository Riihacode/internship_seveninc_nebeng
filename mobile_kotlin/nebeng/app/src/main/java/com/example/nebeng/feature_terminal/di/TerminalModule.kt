package com.example.nebeng.feature_terminal.di

import com.example.nebeng.feature_terminal.data.remote.api.TerminalApi
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import com.example.nebeng.feature_terminal.data.repository.TerminalRepositoryImpl
import com.example.nebeng.feature_terminal.domain.usecase.ReadAllTerminalUseCase
import com.example.nebeng.feature_terminal.domain.usecase.ReadByIdTerminalUseCase
import com.example.nebeng.feature_terminal.domain.usecase.TerminalUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TerminalModule {
    @Provides
    @Singleton
    fun provideTerminalApi(
        retrofit: Retrofit
    ): TerminalApi = retrofit.create(TerminalApi::class.java)

    @Provides
    @Singleton
    fun provideTerminalRepository(
        api: TerminalApi
    ): TerminalRepository = TerminalRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideTerminalUseCases(
        repository: TerminalRepository
    ): TerminalUseCases {
        return TerminalUseCases(
            readAll = ReadAllTerminalUseCase(repository),
            readById = ReadByIdTerminalUseCase(repository)
        )
    }
}