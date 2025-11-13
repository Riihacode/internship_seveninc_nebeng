package com.example.nebeng.feature_a_history_order.domain.usecase

data class HistoryOrderUseCases(
    val getPassengerRideBooking: GetHistoryOrdersUseCase,
    val getTerminal: GetTerminalUseCase,
    val getVehicle: GetVehicleUseCase
)
