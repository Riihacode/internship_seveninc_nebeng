package com.example.nebeng.feature_driver_withdrawal.domain.usecase

data class DriverWithdrawalUseCases(
    val create: CreateDriverWithdrawalUseCase,
    val readAll: ReadAllDriverWithdrawalUseCase,
    val readById: ReadByIdDriverWithdrawalUseCase,
    val readByDriverId: ReadByDriverIdDriverWithdrawalUseCase,
    val readByStatus: ReadByStatusDriverWithdrawalUseCase,
    val update: UpdateDriverWithdrawalUseCase,
    val delete: DeleteDriverWithdrawalUseCase
)
