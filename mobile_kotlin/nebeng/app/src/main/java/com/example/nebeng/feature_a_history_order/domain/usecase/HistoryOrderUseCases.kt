package com.example.nebeng.feature_a_history_order.domain.usecase

data class HistoryOrderUseCases(
//    val getPassengerRideBookingByCustomerId: GetPassengerRideBookingHistoryOrderByCustomerIdUseCase,
//    val getTerminal: GetTerminalUseCase,
//    val getVehicle: GetVehicleUseCase
    val getCustomerHistoryOrder: GetCustomerHistoryOrderUseCase,
    val getDriverHistoryOrder: GetDriverHistoryOrderUseCase
)
