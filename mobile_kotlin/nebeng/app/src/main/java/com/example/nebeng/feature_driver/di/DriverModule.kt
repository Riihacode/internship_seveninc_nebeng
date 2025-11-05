package com.example.nebeng.feature_driver.di

import com.example.nebeng.feature_driver.data.remote.api.DriverApi
import com.example.nebeng.feature_driver.data.repository.DriverRepository
import com.example.nebeng.feature_driver.data.repository.DriverRepositoryImpl
import com.example.nebeng.feature_driver.domain.usecase.CreateDriverUseCase
import com.example.nebeng.feature_driver.domain.usecase.DeleteDriverUseCase
import com.example.nebeng.feature_driver.domain.usecase.DriverUseCases
import com.example.nebeng.feature_driver.domain.usecase.ReadAllDriverUseCase
import com.example.nebeng.feature_driver.domain.usecase.ReadByIdDriverUseCase
import com.example.nebeng.feature_driver.domain.usecase.UpdateDriverUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DriverModule {
    @Provides
    @Singleton
    fun provideDriverApi(
        retrofit: Retrofit
    ): DriverApi = retrofit.create(DriverApi::class.java)

    @Provides
    @Singleton
    fun provideDriverRepository(
        api: DriverApi
    ): DriverRepository = DriverRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDriverUseCase(
        repository: DriverRepository
    ): DriverUseCases {
        return DriverUseCases(
            readAll     = ReadAllDriverUseCase(repository),
            readById    = ReadByIdDriverUseCase(repository),
            create      = CreateDriverUseCase(repository),
            update      = UpdateDriverUseCase(repository),
            delete      = DeleteDriverUseCase(repository)
        )
    }
}