package com.example.nebeng.feature_passenger_ride.di

import com.example.nebeng.feature_passenger_ride.data.remote.api.PassengerRideApi
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepositoryImpl
import com.example.nebeng.feature_passenger_ride.domain.usecase.CreatePassengerRideUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.DeletePassengerRideUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.PassengerRideUseCases
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByDriverIdUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByIdUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByStatusUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideAllUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.UpdatePassengerRideUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PassengerRideModule {
    @Provides
    @Singleton
    fun providePassengerRideApi(
        retrofit: Retrofit
    ): PassengerRideApi = retrofit.create(PassengerRideApi::class.java)

    @Provides
    @Singleton
    fun providePassengerRideRepository(
        api: PassengerRideApi
    ): PassengerRideRepository = PassengerRideRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePassengerRideUseCases(
        repository: PassengerRideRepository
    ): PassengerRideUseCases {
        return PassengerRideUseCases(
            readAll         = ReadPassengerRideAllUseCase(repository),
            readById        = ReadPassengerRideByIdUseCase(repository),
            readByDriverId  = ReadPassengerRideByDriverIdUseCase(repository),
            readByStatus    = ReadPassengerRideByStatusUseCase(repository),
            create          = CreatePassengerRideUseCase(repository),
            update          = UpdatePassengerRideUseCase(repository),
            delete          = DeletePassengerRideUseCase(repository)
        )
    }
}