package com.example.nebeng.feature_a_history_order.di

import com.example.nebeng.feature_a_history_order.domain.usecase.GetCustomerHistoryOrderUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.GetDriverHistoryOrderUseCase
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HistoryOrderModule {
    @Provides
    @Singleton
    fun provideGetHistoryOrderForCustomerUseCase(
        passengerRideBookingRepository: PassengerRideBookingRepository,
        terminalRepository: TerminalRepository
    ): GetCustomerHistoryOrderUseCase {
        return GetCustomerHistoryOrderUseCase(
            passengerRideBookingRepository,
            terminalRepository
        )
    }

    // [DRAFT]
    @Provides
    @Singleton
    fun provideGetHistoryOrderForDriverUseCase(
        passengerRideBookingRepository: PassengerRideBookingRepository
    ): GetDriverHistoryOrderUseCase {
        return GetDriverHistoryOrderUseCase(passengerRideBookingRepository)
    }
}