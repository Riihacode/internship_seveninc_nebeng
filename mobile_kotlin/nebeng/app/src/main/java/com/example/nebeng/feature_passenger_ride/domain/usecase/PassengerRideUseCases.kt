package com.example.nebeng.feature_passenger_ride.domain.usecase

data class PassengerRideUseCases(
    val readAll: ReadPassengerRideUseCase,
    val readById: ReadPassengerRideByIdUseCase,
    val readByDriverId: ReadPassengerRideByDriverIdUseCase,
    val readByStatus: ReadPassengerRideByStatusUseCase,
    val create: CreatePassengerRideUseCase,
    val update: UpdatePassengerRideUseCase,
    val delete: DeletePassengerRideUseCase
)