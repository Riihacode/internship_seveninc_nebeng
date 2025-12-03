package com.example.nebeng.feature_a_homepage.domain.usecase

data class NebengMotorUseCases(
    // GET ALL
    val getAllPassengerRide: GetAllPassengerRideUseCase,
    val getAllPaymentMethod: GetAllPaymentMethodUseCase,
    val getALlPassengerPricing: GetAllPassengerPricingUseCase,
    val getAllTerminal: GetAllTerminalUseCase,

    // GET BY ID
//    val getArrivalTerminal: GetArrivalTerminalUseCase,
//    val getDepartureTerminal: GetDepartureTerminalUseCase,
    val getByIdCustomer: GetByIdCustomerUseCase,
    val getByIdPassengerRideBooking: GetByIdPassengerRideBookingUseCase,
    val getByIdPassengerTransaction: GetByIdPassengerTransactionUseCase,
    val getByIdPaymentMethod: GetByIdPaymentMethod,
    val getByIdPassengerPricing: GetByIdPassengerPricingUseCase,
    val getByIdDriver: GetByIdDriverUseCase,

    // CREATE
    val createPassengerRideBooking: CreatePassengerRideBookingUseCase,
    val createPassengerTransaction: CreatePassengerTransactionUseCase
)