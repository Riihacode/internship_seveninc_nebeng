package com.example.nebeng.feature_passenger_ride_booking.domain.usecase

data class PassengerRideBookingUseCases(
    val readAll: ReadAllPassengerRideBookingUseCase,
    val readById: ReadByIdPassengerRideBookingUseCase,
    val readByCustomerId: ReadByCustomerIdRideBookingUseCase,
    val readByPassengerRideId: ReadByPassengerRideIdPassengerRideBookingUseCase,
    val create: CreatePassengerRideBookingUseCase,
    val update: UpdatePassengerRideBookingUseCase,
    val patch: PatchPassengerRideBookingUseCase,
    val delete: DeletePassengerRideBookingUseCase
)