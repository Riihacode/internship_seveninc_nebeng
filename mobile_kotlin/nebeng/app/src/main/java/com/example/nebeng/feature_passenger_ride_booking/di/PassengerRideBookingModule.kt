package com.example.nebeng.feature_passenger_ride_booking.di

import com.example.nebeng.feature_passenger_ride.data.remote.api.PassengerRideApi
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepository
import com.example.nebeng.feature_passenger_ride.data.repository.PassengerRideRepositoryImpl
import com.example.nebeng.feature_passenger_ride.domain.usecase.CreatePassengerRideUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.DeletePassengerRideUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.PassengerRideUseCases
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideAllUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByDriverIdUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByIdUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.ReadPassengerRideByStatusUseCase
import com.example.nebeng.feature_passenger_ride.domain.usecase.UpdatePassengerRideUseCase
import com.example.nebeng.feature_passenger_ride_booking.data.remote.api.PassengerRideBookingApi
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepositoryImpl
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.CreatePassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.DeletePassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.PassengerRideBookingUseCases
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.PatchPassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.ReadAllPassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.ReadByCustomerIdRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.ReadByIdPassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.ReadByPassengerRideIdPassengerRideBookingUseCase
import com.example.nebeng.feature_passenger_ride_booking.domain.usecase.UpdatePassengerRideBookingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PassengerRideBookingModule {
    @Provides
    @Singleton
    fun providePassengerRideBookingApi(
        retrofit: Retrofit
    ): PassengerRideBookingApi = retrofit.create(PassengerRideBookingApi::class.java)

    @Provides
    @Singleton
    fun providePassengerRideBookingRepository(
        api: PassengerRideBookingApi
    ): PassengerRideBookingRepository = PassengerRideBookingRepositoryImpl(api)


    // ============================
    // ✅ UseCase Provider
    // ============================
    @Provides
    @Singleton
    fun providePassengerRideBookingUseCases(
        repository: PassengerRideBookingRepository
    ): PassengerRideBookingUseCases {
        return PassengerRideBookingUseCases(
            readAll                 = ReadAllPassengerRideBookingUseCase(repository),
            readById                = ReadByIdPassengerRideBookingUseCase(repository),
            readByCustomerId        = ReadByCustomerIdRideBookingUseCase(repository),
            readByPassengerRideId   = ReadByPassengerRideIdPassengerRideBookingUseCase(repository),
            create                  = CreatePassengerRideBookingUseCase(repository),
            update                  = UpdatePassengerRideBookingUseCase(repository),
            patch                   = PatchPassengerRideBookingUseCase(repository),
            delete                  = DeletePassengerRideBookingUseCase(repository)
        )
    }
}