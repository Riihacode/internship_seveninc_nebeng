package com.example.nebeng.feature_vehicle.domain.usecase

data class VehicleUseCases(
    val create: CreateVehicleUseCase,
    val readAll: ReadAllVehicleUseCase,
    val readById: ReadByIdVehicleUseCase,
    val readByDriverId: ReadByDriverIdVehicleUseCase,
    val update: UpdateVehicleUseCase,
    val patchVerifyTrue: PatchVerifyTrueVehicleUseCase,
    val patchVerifyFalse: PatchVerifyFalseVehicleUseCase,
    val delete: DeleteVehicleUseCase
)