package com.example.nebeng.feature_driver_withdrawal.di

import com.example.nebeng.feature_driver_withdrawal.data.remote.api.DriverWithdrawalApi
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepository
import com.example.nebeng.feature_driver_withdrawal.data.repository.DriverWithdrawalRepositoryImpl
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.CreateDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.DeleteDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.DriverWithdrawalUseCases
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.ReadAllDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.ReadByDriverIdDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.ReadByIdDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.ReadByStatusDriverWithdrawalUseCase
import com.example.nebeng.feature_driver_withdrawal.domain.usecase.UpdateDriverWithdrawalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DriverWithdrawalModule {
    @Provides
    @Singleton
    fun provideDriverWithdrawalApi(
        retrofit: Retrofit
    ): DriverWithdrawalApi = retrofit.create(DriverWithdrawalApi::class.java)

    @Provides
    @Singleton
    fun provideDriverWithdrawalRepository(
        api: DriverWithdrawalApi
    ): DriverWithdrawalRepository = DriverWithdrawalRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDriverWithdrawalUseCases(
        repository: DriverWithdrawalRepository
    ): DriverWithdrawalUseCases {
        return DriverWithdrawalUseCases(
            create = CreateDriverWithdrawalUseCase(repository),
            readAll = ReadAllDriverWithdrawalUseCase(repository),
            readById = ReadByIdDriverWithdrawalUseCase(repository),
            readByDriverId = ReadByDriverIdDriverWithdrawalUseCase(repository),
            readByStatus = ReadByStatusDriverWithdrawalUseCase(repository),
            update = UpdateDriverWithdrawalUseCase(repository),
            delete = DeleteDriverWithdrawalUseCase(repository)
        )
    }
}