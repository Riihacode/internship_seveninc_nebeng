package com.example.nebeng.feature_driver.domain.usecase

data class DriverUseCases(
    val create: CreateDriverUseCase,
    val readAll: ReadAllDriverUseCase,
    val readById: ReadByIdDriverUseCase,
    val update: UpdateDriverUseCase,
    val delete: DeleteDriverUseCase
)