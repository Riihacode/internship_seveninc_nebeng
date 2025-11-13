package com.example.nebeng.feature_a_history_order.di

import com.example.nebeng.feature_a_history_order.domain.usecase.GetHistoryOrdersUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.GetTerminalUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.GetVehicleUseCase
import com.example.nebeng.feature_a_history_order.domain.usecase.HistoryOrderUseCases
import com.example.nebeng.feature_passenger_ride_booking.data.repository.PassengerRideBookingRepository
import com.example.nebeng.feature_terminal.data.repository.TerminalRepository
import com.example.nebeng.feature_vehicle.data.repository.VehicleRepository
import com.example.nebeng.feature_vehicle.domain.usecase.ReadAllVehicleUseCase
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
    fun provideHistoryOrderUseCases(
        bookingRepository: PassengerRideBookingRepository,
        terminalRepository: TerminalRepository,
        vehicleRepository: VehicleRepository
    ): HistoryOrderUseCases {
        return HistoryOrderUseCases(
            getPassengerRideBooking = GetHistoryOrdersUseCase(bookingRepository),
            getTerminal             = GetTerminalUseCase(terminalRepository),
            getVehicle              = GetVehicleUseCase(vehicleRepository)
        )
    }
}