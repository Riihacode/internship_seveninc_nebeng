package com.example.nebeng.feature_vehicle.di

import com.example.nebeng.feature_vehicle.data.remote.api.VehicleApi
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepositoryImpl
import com.example.nebeng.feature_vehicle.domain.usecase.CreateVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.DeleteVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.PatchVerifyFalseVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.PatchVerifyTrueVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.ReadAllVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.ReadByDriverIdVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.ReadByIdVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.UpdateVehicleUseCase
import com.example.nebeng.feature_vehicle.domain.usecase.VehicleUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VehicleModule {
    @Provides
    @Singleton
    fun provideVehicleApi(
        retrofit: Retrofit
    ): VehicleApi = retrofit.create(VehicleApi::class.java)

    @Provides
    @Singleton
    fun provideVehicleRepository(
        api: VehicleApi
    ): VehicleRepository = VehicleRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideVehicleUseCases(
        repository: VehicleRepository
    ): VehicleUseCases {
        return VehicleUseCases(
            create = CreateVehicleUseCase(repository),
            readAll = ReadAllVehicleUseCase(repository),
            readById = ReadByIdVehicleUseCase(repository),
            readByDriverId = ReadByDriverIdVehicleUseCase(repository),
            update = UpdateVehicleUseCase(repository),
            patchVerifyTrue = PatchVerifyTrueVehicleUseCase(repository),
            patchVerifyFalse = PatchVerifyFalseVehicleUseCase(repository),
            delete = DeleteVehicleUseCase(repository)
        )
    }
}