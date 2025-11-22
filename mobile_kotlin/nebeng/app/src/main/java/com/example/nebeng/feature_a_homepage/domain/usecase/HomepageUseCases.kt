package com.example.nebeng.feature_a_homepage.domain.usecase

data class HomepageUseCases(
    val getAllPassengerRide: GetAllPassengerRideUseCase,
    val getAllPaymentMethod: GetAllPaymentMethodUseCase,
    val getAllTerminal: GetAllTerminalUseCase,
    val getArrivalTerminal: GetArrivalTerminalUseCase,
    val getDepartureTerminal: GetDepartureTerminalUseCase,
    val getByIdCustomer: GetByIdCustomerUseCase,
    val getByIdPassengerRideBooking: GetByIdPassengerRideBookingUseCase,
    val getByIdPassengerTransaction: GetByIdPassengerTransactionUseCase,
    val getByIdPaymentMethod: GetByIdPaymentMethod,
    val getByIdDriver: GetByIdDriverUseCase
)
