package com.example.nebeng.feature_passenger_transaction.domain.usecase

data class PassengerTransactionUseCases(
    val create: CreatePassengerTransactionUseCase,
    val readAll: ReadAllPassengerTransactionUseCase,
    val readById: ReadByIdPassengerTransactionUseCase,
    val readByPassengerRideBookingId: ReadByPassengerRideBookingIdPassengerTransactionUseCase,
    val update: UpdatePassengerTransactionUseCase,
    val patchStatus: PatchStatusByIdPassengerTransactionUseCase,
    val delete: DeletePassengerTransactionUseCase
)