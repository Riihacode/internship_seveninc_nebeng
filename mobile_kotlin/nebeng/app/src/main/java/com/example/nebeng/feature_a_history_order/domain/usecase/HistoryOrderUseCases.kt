package com.example.nebeng.feature_a_history_order.domain.usecase

data class HistoryOrderUseCases(
    val getPassengerRideBooking: GetPassengerRideBookingHistoryOrdersUseCase,
    val getTerminal: GetTerminalUseCase,
    val getVehicle: GetVehicleUseCase
)
